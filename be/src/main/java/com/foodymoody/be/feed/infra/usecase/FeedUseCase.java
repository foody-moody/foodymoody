package com.foodymoody.be.feed.infra.usecase;

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
import com.foodymoody.be.feed.application.FeedCommentCountReadService;
import com.foodymoody.be.feed.application.FeedMapper;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.application.FeedWriteService;
import com.foodymoody.be.feed.application.StoreMoodReadService;
import com.foodymoody.be.feed.application.dto.request.FeedServiceDeleteRequest;
import com.foodymoody.be.feed.application.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.application.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.application.dto.request.ImageMenuPair;
import com.foodymoody.be.feed.application.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.application.dto.response.FeedMemberResponse;
import com.foodymoody.be.feed.application.dto.response.FeedReadAllResponse;
import com.foodymoody.be.feed.application.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.application.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.ImageMenu;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import com.foodymoody.be.feed.infra.usecase.dto.ImageIdNamePair;
import com.foodymoody.be.feed.infra.usecase.dto.MenuNameRatingPair;
import com.foodymoody.be.feed_like_count.application.FeedLikeCountService;
import com.foodymoody.be.feed_like_count.domain.entity.FeedLikeCount;
import com.foodymoody.be.image.application.ImageService;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.member.application.MemberQueryService;
import com.foodymoody.be.member.application.dto.FeedAuthorSummary;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.menu.application.MenuService;
import com.foodymoody.be.menu.domain.entity.Menu;
import com.foodymoody.be.store.application.StoreReadService;
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
    private final MemberQueryService memberQueryService;
    private final MenuService menuService;
    private final StoreMoodReadService storeMoodReadService;
    private final FeedLikeCountService feedLikeCountService;
    private final FeedCommentCountReadService feedCommentCountReadService;
    private final StoreReadService storeReadService;

    @Transactional
    public FeedRegisterResponse register(FeedServiceRegisterRequest request) {
        Member member = memberQueryService.findById(request.getMemberId());
        MemberId memberId = member.getId();
        List<ImageMenuPair> imageMenuPairs = request.getImages();
        List<Menu> menus = toMenu(imageMenuPairs);
        List<Image> images = toImage(imageMenuPairs, memberId);
        List<StoreMoodId> storeMoodIds = request.getStoreMoodIds();
        List<StoreMood> storeMoods = storeMoodReadService.fetchAllByStoreMoodIds(storeMoodIds);
        StoreId storeId = request.getStoreId();
        String review = request.getReview();

        String profileImageUrl = imageService.findById(member.getProfileImageId()).getUrl();

        Feed feed = FeedMapper.toFeed(IdFactory.createFeedId(), memberId, storeId, review, storeMoods, images, menus,
                profileImageUrl);
        Feed savedFeed = feedWriteService.save(feed);

        feedLikeCountService.save(new FeedLikeCount(IdFactory.createFeedLikeCountId(), savedFeed.getId(), 0));

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
            responses = makeFeedReadAllResponseListWhenMemberIdIsNotNull(feeds);
        }

        return new SliceImpl<>(responses, pageable, feeds.hasNext());
    }

    private List<FeedReadAllResponse> makeFeedReadAllResponseListWhenMemberIdIsNull(Slice<Feed> feeds) {
        return feeds.stream()
                .map(feed -> makeFeedReadAllResponse(feed, makeFeedMemberResponse(feed),
                        makeFeedStoreMoodResponses(feed.getStoreMoods()),
                        makeFeedImageMenuResponses(feed),
                        false,
                        findCommentCount(feed.getId()),
                        storeReadService.fetchDetails(feed.getStoreId()).getAddress()))
                .collect(Collectors.toList());
    }

    private List<FeedReadAllResponse> makeFeedReadAllResponseListWhenMemberIdIsNotNull(Slice<Feed> feeds) {
        return feeds.stream()
                .map(feed -> makeFeedReadAllResponse(feed, makeFeedMemberResponse(feed),
                        makeFeedStoreMoodResponses(feed.getStoreMoods()),
                        makeFeedImageMenuResponses(feed),
                        feedReadService.fetchIsLikedByMemberId(feed.getId(), feed.getMemberId()),
                        findCommentCount(feed.getId()),
                        storeReadService.fetchDetails(feed.getStoreId()).getAddress()))
                .collect(Collectors.toList());
    }

    public FeedReadResponse read(FeedId id, MemberId memberId) {
        Feed feed = feedReadService.findFeed(id);
        List<FeedImageMenuResponse> images = makeFeedImageMenuResponses(feed);
        List<StoreMood> storeMoods = feed.getStoreMoods();

        FeedMemberResponse feedMemberResponse = makeFeedMemberResponse(feed);

        if (memberId == null) {
            return FeedMapper.toFeedReadResponse(feedMemberResponse, feed, images,
                    makeFeedStoreMoodResponses(storeMoods),
                    false,
                    findCommentCount(feed.getId()),
                    storeReadService.fetchDetails(feed.getStoreId()).getAddress());
        }

        return FeedMapper.toFeedReadResponse(feedMemberResponse, feed, images,
                makeFeedStoreMoodResponses(storeMoods),
                feedReadService.fetchIsLikedByMemberId(feed.getId(), feed.getMemberId()),
                findCommentCount(feed.getId()), storeReadService.fetchDetails(feed.getStoreId()).getAddress());
    }

    @Transactional
    public void update(FeedServiceUpdateRequest request) {
        Feed feed = feedReadService.findFeed(request.getId());
        Member member = memberQueryService.findById(request.getMemberId());
        MemberId memberId = member.getId();
        List<Image> newImages = toImage(request.getImages(), memberId);
        List<Menu> newMenus = toMenu(request.getImages());
        List<StoreMood> newStoreMoods = storeMoodReadService.fetchAllByStoreMoodIds(request.getStoreMoodIds());
        String profileImageUrl = imageService.findById(member.getProfileImageId()).getUrl();

        feed.update(memberId, request.getStoreId(), request.getReview(), newStoreMoods, newImages, newMenus,
                profileImageUrl, feed.getCreatedAt(), LocalDateTime.now());
    }

    @Transactional
    public void delete(FeedServiceDeleteRequest request) {
        FeedId feedId = request.getId();
        MemberId memberId = memberQueryService.findById(request.getMemberId()).getId();

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
                        new Menu(IdFactory.createMenuId(), imageMenuPair.getMenu().getName(),
                                imageMenuPair.getMenu().getRating())))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Image> toImage(List<ImageMenuPair> imageMenuPairs, MemberId memberId) {
        return imageMenuPairs.stream()
                .map(imageMenuPair -> new Image(imageMenuPair.getImageId(),
                        imageService.findById(imageMenuPair.getImageId()).getUrl(),
                        memberId))
                .collect(Collectors.toUnmodifiableList());
    }

    private FeedMemberResponse makeFeedMemberResponse(Feed feed) {
        FeedAuthorSummary memberData = memberQueryService.fetchFeedAuthorSummaryById(feed.getMemberId());
        return toFeedMemberResponse(memberData);
    }

    private List<FeedImageMenuResponse> makeFeedImageMenuResponses(Feed feed) {
        List<ImageMenu> imageMenus = feed.getImageMenus();

        List<ImageIdNamePair> imageIdUrlList = feedReadService.fetchImageIdUrlList(imageMenus);
        List<MenuNameRatingPair> menuNameRatingList = feedReadService.fetchMenuNameRatingList(imageMenus);

        return FeedMapper.toFeedImageMenuResponses(imageIdUrlList, menuNameRatingList);
    }

    public Long findCommentCount(FeedId feedId) {
        // TODO: comment쪽에서 댓글이 등록되면 feed에 저장되도록 리팩토링
        return feedCommentCountReadService.fetchCountByFeedId(feedId);
    }

}
