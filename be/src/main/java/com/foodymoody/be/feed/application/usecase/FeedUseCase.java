package com.foodymoody.be.feed.application.usecase;

import static com.foodymoody.be.feed.application.FeedMapper.makeFeedReadAllResponse;
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
@Transactional(readOnly = true)
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

    public Slice<FeedReadAllResponse> readAll(Pageable pageable, MemberId memberId) {
        final String sortBy = "createdAt";
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy).descending());

        Slice<Feed> feeds = feedReadService.findAll(pageable);

        List<FeedReadAllResponse> responses;
        if (memberId == null) {
            responses = makeFeedReadAllResponseListWhenMemberIdIsNull(feeds);
        } else {
            responses = makeFeedReadAllResponseListWhenMemberIdIsNotNull(feeds, memberId);
        }

        return new SliceImpl<>(responses, pageable, feeds.hasNext());
    }

    private List<FeedReadAllResponse> makeFeedReadAllResponseListWhenMemberIdIsNull(Slice<Feed> feeds) {
        return feeds.stream()
                .map(feed -> makeFeedReadAllResponse(
                        feed,
                        makeFeedMemberResponse(feed),
                        makeFeedStoreMoodResponses(feed.getStoreMoods()),
                        makeFeedImageMenuResponses(feed),
                        false,
                        findCommentCount(feed.getId()),
                        FeedMapper.makeStoreResponse(
                                feed.getStoreId(),
                                storeReadService.findById(feed.getStoreId()).getName())
                        )
                )
                .collect(Collectors.toList());
    }

    private List<FeedReadAllResponse> makeFeedReadAllResponseListWhenMemberIdIsNotNull(
            Slice<Feed> feeds,
            MemberId memberId
    ) {
        return feeds.stream()
                .map(feed -> makeFeedReadAllResponse(
                        feed,
                        makeFeedMemberResponse(feed),
                        makeFeedStoreMoodResponses(feed.getStoreMoods()),
                        makeFeedImageMenuResponses(feed),
                        feedLikeService.fetchIsLiked(feed.getId(), memberId),
                        findCommentCount(feed.getId()),
                        FeedMapper.makeStoreResponse(
                                feed.getStoreId(),
                                storeReadService.findById(feed.getStoreId()).getName())
                        )
                )
                .collect(Collectors.toList());
    }

    public FeedReadResponse read(FeedId id, MemberId memberId) {
        Feed feed = feedReadService.findFeed(id);
        List<FeedImageMenuResponse> images = makeFeedImageMenuResponses(feed);
        List<StoreMood> storeMoods = feed.getStoreMoods();
        FeedMemberResponse feedMemberResponse = makeFeedMemberResponse(feed);
        boolean isLiked = feedLikeService.fetchIsLiked(feed.getId(), memberId);

        if (memberId == null) {
            return FeedMapper.toFeedReadResponse(
                    feedMemberResponse,
                    feed,
                    images,
                    makeFeedStoreMoodResponses(storeMoods),
                    false,
                    findCommentCount(feed.getId()),
                    FeedMapper.makeStoreResponse(
                            feed.getStoreId(),
                            storeReadService.findById(feed.getStoreId()).getName()
                    )
            );
        }

        return FeedMapper.toFeedReadResponse(
                feedMemberResponse,
                feed,
                images,
                makeFeedStoreMoodResponses(storeMoods),
                isLiked,
                findCommentCount(feed.getId()),
                FeedMapper.makeStoreResponse(
                        feed.getStoreId(),
                        storeReadService.findById(feed.getStoreId()).getName()
                )
        );
    }

    @Transactional
    public void update(FeedServiceUpdateRequest request) {
        Feed feed = feedReadService.findFeed(request.getId());
        Member member = memberReadService.findById(request.getMemberId());
        MemberId memberId = member.getId();
        List<Image> newImages = toImage(request.getImages(), memberId);
        List<Menu> newMenus = toMenu(request.getImages());
        List<StoreMood> newStoreMoods = storeMoodReadService.fetchAllByStoreMoodIds(request.getStoreMoodIds());
        String profileImageUrl = imageService.findById(member.getProfileImageId()).getUrl();

        feed.update(
                memberId,
                request.getStoreId(),
                request.getReview(),
                newStoreMoods,
                newImages,
                newMenus,
                profileImageUrl,
                feed.getCreatedAt(),
                LocalDateTime.now()
        );
    }

    @Transactional
    public void delete(FeedServiceDeleteRequest request) {
        FeedId feedId = request.getId();
        MemberId memberId = memberReadService.findById(request.getMemberId()).getId();

        validateFeedMember(feedId, memberId);

        List<ImageMenu> imageMenus = feedReadService.findFeed(feedId).getImageMenus();
        List<ImageId> imageIds = makeImageIds(imageMenus);

        imageService.softDelete(memberId, imageIds);
        feedWriteService.deleteById(request.getId());
    }

    private void validateFeedMember(FeedId feedId, MemberId memberId) {
        if (!feedReadService.findFeed(feedId).getMemberId().equals(memberId)) {
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
                .map(imageMenuPair -> new Image(
                        imageMenuPair.getImageId(),
                        imageService.findById(imageMenuPair.getImageId()).getUrl(),
                        memberId)
                )
                .collect(Collectors.toUnmodifiableList());
    }

    private FeedMemberResponse makeFeedMemberResponse(Feed feed) {
        FeedAuthorSummary memberData = memberReadService.fetchFeedAuthorSummaryById(feed.getMemberId());
        return toFeedMemberResponse(memberData);
    }

    private List<FeedImageMenuResponse> makeFeedImageMenuResponses(Feed feed) {
        List<ImageMenu> imageMenus = feed.getImageMenus();

        List<ImageIdNamePair> imageIdUrlList = feedReadService.fetchImageIdUrlList(imageMenus);
        List<MenuNameRatingPair> menuNameRatingList = feedReadService.fetchMenuNameRatingList(imageMenus);

        return FeedMapper.toFeedImageMenuResponses(imageIdUrlList, menuNameRatingList);
    }

    public Long findCommentCount(FeedId feedId) {
        return feedCommentCountReadService.fetchCountByFeedId(feedId);
    }

}
