package com.foodymoody.be.feed.application.usecase;

import static com.foodymoody.be.feed.application.FeedMapper.makeFeedStoreMoodResponses;
import static com.foodymoody.be.feed.application.FeedMapper.makeImageIds;
import static com.foodymoody.be.feed.application.FeedMapper.toFeedMemberResponse;

import com.foodymoody.be.common.exception.FeedIdNotExistsException;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.common.util.ids.StoreMoodId;
import com.foodymoody.be.feed.application.FeedMapper;
import com.foodymoody.be.feed.application.dto.request.FeedServiceDeleteRequest;
import com.foodymoody.be.feed.application.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.application.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.application.dto.request.ImageMenuPair;
import com.foodymoody.be.feed.application.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.application.dto.response.FeedMemberResponse;
import com.foodymoody.be.feed.application.dto.response.FeedReadAllResponse;
import com.foodymoody.be.feed.application.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.application.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.application.service.FeedCommentCountReadService;
import com.foodymoody.be.feed.application.service.FeedReadService;
import com.foodymoody.be.feed.application.service.FeedWriteService;
import com.foodymoody.be.feed.application.service.StoreMoodReadService;
import com.foodymoody.be.feed.application.usecase.dto.ImageIdNamePair;
import com.foodymoody.be.feed.application.usecase.dto.MenuNameRatingPair;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.ImageMenu;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import com.foodymoody.be.feed_like.application.service.FeedLikeService;
import com.foodymoody.be.feed_like_count.application.service.FeedLikeCountService;
import com.foodymoody.be.feed_like_count.domain.entity.FeedLikeCount;
import com.foodymoody.be.image.application.service.ImageService;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.member.application.dto.FeedAuthorSummary;
import com.foodymoody.be.member.application.service.MemberReadService;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.menu.application.service.MenuService;
import com.foodymoody.be.menu.domain.entity.Menu;
import com.foodymoody.be.store.application.service.StoreReadService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedUseCase {

    private final FeedReadService feedReadService;
    private final FeedWriteService feedWriteService;
    private final ImageService imageService;
    private final MemberReadService memberReadService;
    private final MenuService menuService;
    private final StoreMoodReadService storeMoodReadService;
    private final FeedLikeCountService feedLikeCountService;
    private final FeedLikeService feedLikeService;
    private final FeedCommentCountReadService feedCommentCountReadService;
    private final StoreReadService storeReadService;

    @Transactional
    public FeedRegisterResponse register(FeedServiceRegisterRequest request) {
        MemberId memberId = request.getMemberId();
        Member member = memberReadService.findById(memberId);
        List<ImageMenuPair> imageMenuPairs = request.getImages();
        List<Menu> menus = toMenu(imageMenuPairs);
        List<Image> images = toImage(imageMenuPairs, memberId);
        List<StoreMoodId> storeMoodIds = request.getStoreMoodIds();
        List<StoreMood> storeMoods = storeMoodReadService.fetchAllByStoreMoodIds(storeMoodIds);
        StoreId storeId = request.getStoreId();
        String review = request.getReview();
        ImageId profileImageId = member.getProfileImageId();
        String profileImageUrl = imageService.findById(profileImageId).getUrl();

        Feed feed = Feed.builder()
                .id(IdFactory.createFeedId())
                .memberId(memberId)
                .storeId(storeId)
                .review(review)
                .storeMoods(storeMoods)
                .images(images)
                .menus(menus)
                .profileImageUrl(profileImageUrl)
                .build();

        Feed savedFeed = feedWriteService.save(feed);
        FeedLikeCount feedLikeCount = FeedMapper.makeFeedLikeCount(
                IdFactory.createFeedLikeCountId(),
                savedFeed.getId(),
                0
        );

        feedLikeCountService.save(feedLikeCount);

        return FeedMapper.toFeedRegisterResponse(savedFeed);
    }

    @Transactional(readOnly = true)
    public Slice<FeedReadAllResponse> readAll(Pageable pageable, MemberId memberId) {
        final String sortBy = "createdAt";
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy).descending());

        Slice<Feed> feeds = feedReadService.findAll(pageable);

        List<FeedReadAllResponse> responses;
        if (memberId == null) {
            responses = makeFeedReadAllResponseList(feeds);
        } else {
            responses = makeFeedReadAllResponseList(feeds, memberId);
        }

        return new SliceImpl<>(responses, pageable, feeds.hasNext());
    }

    private List<FeedReadAllResponse> makeFeedReadAllResponseList(Slice<Feed> feeds) {
        return feeds.stream()
                .map(feed -> {
                            final boolean isLiked = false;
                            MemberId memberId = feed.getMemberId();
                            List<StoreMood> storeMoods = feed.getStoreMoods();
                            List<ImageMenu> imageMenus = feed.getImageMenus();
                            String review = feed.getReview();
                            FeedId id = feed.getId();
                            LocalDateTime createdAt = feed.getCreatedAt();
                            LocalDateTime updatedAt = feed.getUpdatedAt();
                            int likeCount = feed.getLikeCount();
                            StoreId storeId = feed.getStoreId();
                            String storeName = storeReadService.findById(storeId).getName();
                            Long commentCount = findCommentCount(id);

                            var storeResponse = FeedMapper.makeStoreResponse(storeId, storeName);
                            var feedMemberResponse = makeFeedMemberResponse(memberId);
                            var feedStoreMoodResponses = makeFeedStoreMoodResponses(storeMoods);
                            var feedImageMenuResponses = makeFeedImageMenuResponses(imageMenus);

                            return FeedReadAllResponse.builder()
                                    .id(id)
                                    .member(feedMemberResponse)
                                    .createdAt(createdAt)
                                    .updatedAt(updatedAt)
                                    .storeResponse(storeResponse)
                                    .review(review)
                                    .storeMood(feedStoreMoodResponses)
                                    .images(feedImageMenuResponses)
                                    .likeCount(likeCount)
                                    .isLiked(isLiked)
                                    .commentCount(commentCount)
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }

    private List<FeedReadAllResponse> makeFeedReadAllResponseList(
            Slice<Feed> feeds,
            MemberId memberId
    ) {
        return feeds.stream()
                .map(feed -> {
                            MemberId feedMemberId = feed.getMemberId();
                            List<StoreMood> storeMoods = feed.getStoreMoods();
                            List<ImageMenu> imageMenus = feed.getImageMenus();
                            FeedId id = feed.getId();
                            StoreId storeId = feed.getStoreId();
                            String storeName = storeReadService.findById(storeId).getName();
                            String review = feed.getReview();
                            LocalDateTime createdAt = feed.getCreatedAt();
                            LocalDateTime updatedAt = feed.getUpdatedAt();
                            int likeCount = feed.getLikeCount();
                            boolean isLiked = feedLikeService.fetchIsLiked(id, memberId);
                            Long commentCount = findCommentCount(id);

                            var feedMemberResponse = makeFeedMemberResponse(feedMemberId);
                            var feedStoreMoodResponses = makeFeedStoreMoodResponses(storeMoods);
                            var storeResponse = FeedMapper.makeStoreResponse(storeId, storeName);
                            var feedImageMenuResponses = makeFeedImageMenuResponses(imageMenus);

                            return FeedReadAllResponse.builder()
                                    .id(id)
                                    .member(feedMemberResponse)
                                    .createdAt(createdAt)
                                    .updatedAt(updatedAt)
                                    .storeResponse(storeResponse)
                                    .review(review)
                                    .storeMood(feedStoreMoodResponses)
                                    .images(feedImageMenuResponses)
                                    .likeCount(likeCount)
                                    .isLiked(isLiked)
                                    .commentCount(commentCount)
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FeedReadResponse read(FeedId id, MemberId memberId) {
        Feed feed = feedReadService.findFeed(id);
        List<ImageMenu> imageMenus = feed.getImageMenus();
        List<StoreMood> storeMoods = feed.getStoreMoods();
        MemberId feedMemberId = feed.getMemberId();
        boolean isLiked = feedLikeService.fetchIsLiked(id, memberId);
        String review = feed.getReview();
        LocalDateTime createdAt = feed.getCreatedAt();
        LocalDateTime updatedAt = feed.getUpdatedAt();
        int likeCount = feed.getLikeCount();
        StoreId storeId = feed.getStoreId();
        String storeName = storeReadService.findById(storeId).getName();
        Long commentCount = findCommentCount(id);

        var feedImageMenuResponses = makeFeedImageMenuResponses(imageMenus);
        var feedMemberResponse = makeFeedMemberResponse(feedMemberId);
        var feedStoreMoodResponses = makeFeedStoreMoodResponses(storeMoods);
        var storeResponse = FeedMapper.makeStoreResponse(storeId, storeName);

        if (memberId == null) {
            return FeedReadResponse.builder()
                    .id(id)
                    .member(feedMemberResponse)
                    .storeResponse(storeResponse)
                    .createdAt(createdAt)
                    .updatedAt(updatedAt)
                    .review(review)
                    .storeMood(feedStoreMoodResponses)
                    .images(feedImageMenuResponses)
                    .likeCount(likeCount)
                    .isLiked(false)
                    .commentCount(commentCount)
                    .build();
        }

        return FeedReadResponse.builder()
                .id(id)
                .member(feedMemberResponse)
                .storeResponse(storeResponse)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .review(review)
                .storeMood(feedStoreMoodResponses)
                .images(feedImageMenuResponses)
                .likeCount(likeCount)
                .isLiked(isLiked)
                .commentCount(commentCount)
                .build();
    }

    @Transactional
    public void update(FeedServiceUpdateRequest request) {
        Feed feed = feedReadService.findFeed(request.getId());
        FeedId feedId = feed.getId();
        Member member = memberReadService.findById(request.getMemberId());
        MemberId memberId = member.getId();
        List<Image> newImages = toImage(request.getImages(), memberId);
        List<Menu> newMenus = toMenu(request.getImages());
        List<StoreMood> newStoreMoods = storeMoodReadService.fetchAllByStoreMoodIds(request.getStoreMoodIds());
        String profileImageUrl = imageService.findById(member.getProfileImageId()).getUrl();
        StoreId storeId = request.getStoreId();
        String review = request.getReview();
        LocalDateTime createdAt = feed.getCreatedAt();
        final LocalDateTime now = LocalDateTime.now();

        validateFeedMember(feedId, memberId);

        feed.update(
                memberId,
                storeId,
                review,
                newStoreMoods,
                newImages,
                newMenus,
                profileImageUrl,
                createdAt,
                now
        );
    }

    @Transactional
    public void delete(FeedServiceDeleteRequest request) {
        FeedId feedId = request.getId();
        MemberId memberId = memberReadService.findById(request.getMemberId()).getId();
        List<ImageMenu> imageMenus = feedReadService.findFeed(feedId).getImageMenus();
        List<ImageId> imageIds = makeImageIds(imageMenus);

        validateFeedMember(feedId, memberId);

        imageService.softDelete(memberId, imageIds);
        feedWriteService.deleteById(request.getId());
    }

    private void validateFeedMember(FeedId feedId, MemberId memberId) {
        Feed feed = feedReadService.findFeed(feedId);
        MemberId feedMemberId = feed.getMemberId();

        if (!feedMemberId.equals(memberId)) {
            throw new FeedIdNotExistsException();
        }
    }

    // TODO: 쿼리 사용하여 리팩토링
    private List<Menu> toMenu(List<ImageMenuPair> imageMenuPairs) {
        return imageMenuPairs.stream()
                .map(imageMenuPair -> menuService.save(
                        new Menu(
                                IdFactory.createMenuId(),
                                imageMenuPair.getMenu().getName(),
                                imageMenuPair.getMenu().getRating()))
                )
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Image> toImage(List<ImageMenuPair> imageMenuPairs, MemberId memberId) {
        return imageMenuPairs.stream()
                .map(imageMenuPair -> {
                            ImageId imageId = imageMenuPair.getImageId();
                            String url = imageService.findById(imageId).getUrl();
                            return new Image(
                                    imageId,
                                    url,
                                    memberId
                            );
                        }
                )
                .collect(Collectors.toUnmodifiableList());
    }

    private FeedMemberResponse makeFeedMemberResponse(MemberId memberId) {
        FeedAuthorSummary memberData = memberReadService.fetchFeedAuthorSummaryById(memberId);
        return toFeedMemberResponse(memberData);
    }

    private List<FeedImageMenuResponse> makeFeedImageMenuResponses(List<ImageMenu> imageMenus) {
        List<ImageIdNamePair> imageIdUrlList = feedReadService.fetchImageIdUrlList(imageMenus);
        List<MenuNameRatingPair> menuNameRatingList = feedReadService.fetchMenuNameRatingList(imageMenus);
        return FeedMapper.toFeedImageMenuResponses(imageIdUrlList, menuNameRatingList);
    }

    private Long findCommentCount(FeedId feedId) {
        return feedCommentCountReadService.fetchCountByFeedId(feedId);
    }

}
