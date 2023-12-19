package com.foodymoody.be.feed.infra.usecase;

import static com.foodymoody.be.feed.application.FeedMapper.makeFeedReadAllResponse;
import static com.foodymoody.be.feed.application.FeedMapper.makeFeedStoreMoodResponses;
import static com.foodymoody.be.feed.application.FeedMapper.toFeedMemberResponse;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
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
import com.foodymoody.be.feed_heart_count.domain.FeedHeartCount;
import com.foodymoody.be.feed_heart_count.service.FeedHeartCountService;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.service.ImageService;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.repository.MemberFeedData;
import com.foodymoody.be.member.service.MemberService;
import com.foodymoody.be.menu.domain.Menu;
import com.foodymoody.be.menu.service.MenuService;
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
    private final MemberService memberService;
    private final MenuService menuService;
    private final StoreMoodReadService storeMoodReadService;
    private final FeedHeartCountService feedHeartCountService;

    @Transactional
    public FeedRegisterResponse register(FeedServiceRegisterRequest request) {
        Member member = memberService.findById(IdFactory.createMemberId(request.getMemberId()));
        MemberId memberId = member.getId();
        List<ImageMenuPair> imageMenuPairs = request.getImages();
        List<Menu> menus = toMenu(imageMenuPairs);
        List<Image> images = toImage(imageMenuPairs, memberId);
        // TODO: Request의 Id를 가지고 StoreMood 객체를 만들어서 넘겨야 하는데,
        //  그렇게 하려면 Request의 StoreMoodId가 data.sql에 있는 StoreMoodId와 동일해야 함
        //  하지만 data.sql에는 IdFactory로 암호화된 Id가 들어있지 않으니 fetch 할 때 id 불일치로 당연히 오류가 발생
        //  이런 경우는 어떻게 하는 게 좋을까요??
        List<StoreMood> storeMoods = storeMoodReadService.fetchAllByStoreMoodIds(request.getStoreMoodIds());

        String profileImageUrl = imageService.findById(member.getProfileImageId()).getUrl();

        Feed feed = FeedMapper.toFeed(IdFactory.createFeedId(), memberId, request, storeMoods, images, menus,
                profileImageUrl);
        Feed savedFeed = feedWriteService.save(feed);

        feedHeartCountService.save(new FeedHeartCount(IdFactory.createFeedHeartCountId(), savedFeed.getId(), 0));

        return FeedMapper.toFeedRegisterResponse(savedFeed);
    }

    public Slice<FeedReadAllResponse> readAll(Pageable pageable) {
        final String sortBy = "createdAt";
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy).descending());

        Slice<Feed> feeds = feedReadService.findAll(pageable);
        List<FeedReadAllResponse> responses = makeFeedReadAllResponseList(feeds);

        return new SliceImpl<>(responses, pageable, feeds.hasNext());
    }

    private List<FeedReadAllResponse> makeFeedReadAllResponseList(Slice<Feed> feeds) {
        return feeds.stream()
                .map(feed -> makeFeedReadAllResponse(feed, makeFeedMemberResponse(feed),
                        makeFeedStoreMoodResponses(feed.getStoreMoods()),
                        makeFeedImageMenuResponses(feed)))
                .collect(Collectors.toList());
    }

    public FeedReadResponse read(String id) {
        FeedId feedId = IdFactory.createFeedId(id);
        Feed feed = feedReadService.findFeed(feedId);
        List<FeedImageMenuResponse> images = makeFeedImageMenuResponses(feed);
        List<StoreMood> storeMoods = feed.getStoreMoods();

        FeedMemberResponse feedMemberResponse = makeFeedMemberResponse(feed);

        return FeedMapper.toFeedReadResponse(feedMemberResponse, feed, images,
                makeFeedStoreMoodResponses(storeMoods));
    }

    @Transactional
    public void update(String id, FeedServiceUpdateRequest request) {
        FeedId feedId = IdFactory.createFeedId(id);
        Feed feed = feedReadService.findFeed(feedId);
        Member member = memberService.findById(IdFactory.createMemberId(request.getMemberId()));
        MemberId memberId = member.getId();
        List<Image> newImages = toImage(request.getImages(), memberId);
        List<Menu> newMenus = toMenu(request.getImages());
        List<StoreMood> newStoreMoods = storeMoodReadService.fetchAllByStoreMoodIds(request.getStoreMoodIds());
        String profileImageUrl = imageService.findById(member.getProfileImageId()).getUrl();

        feed.update(memberId, request.getLocation(), request.getReview(), newStoreMoods, newImages, newMenus,
                profileImageUrl);
    }

    @Transactional
    public void delete(FeedServiceDeleteRequest request) {
        FeedId feedId = IdFactory.createFeedId(request.getId());
        MemberId memberId = memberService.findById(IdFactory.createMemberId(request.getMemberId())).getId();

        if (!feedReadService.findFeed(feedId).getMemberId().isSame(memberId)) {
            throw new IllegalArgumentException("이 피드를 작성한 회원이 아닙니다.");
        }

        feedWriteService.deleteById(IdFactory.createFeedId(request.getId()));
    }

    // TODO: 쿼리 사용하여 리팩토링
    public List<Menu> toMenu(List<ImageMenuPair> imageMenuPairs) {
        return imageMenuPairs.stream()
                .map(imageMenuPair -> menuService.save(
                        new Menu(IdFactory.createMenuId(), imageMenuPair.getMenu().getName(),
                                imageMenuPair.getMenu().getRating())))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Image> toImage(List<ImageMenuPair> imageMenuPairs, MemberId memberId) {
        return imageMenuPairs.stream()
                .map(imageMenuPair -> new Image(IdFactory.createImageId(imageMenuPair.getImageId()),
                        imageService.findById(IdFactory.createImageId(imageMenuPair.getImageId())).getUrl(),
                        memberId))
                .collect(Collectors.toUnmodifiableList());
    }

    public FeedMemberResponse makeFeedMemberResponse(Feed feed) {
        MemberFeedData memberData = memberService.fetchFeedDataById(feed.getMemberId());
        return toFeedMemberResponse(memberData);
    }

    public List<FeedImageMenuResponse> makeFeedImageMenuResponses(Feed feed) {
        List<ImageMenu> imageMenus = feed.getImageMenus();

        List<ImageIdNamePair> imageIdUrlList = feedReadService.fetchImageIdUrlList(imageMenus);
        List<MenuNameRatingPair> menuNameRatingList = feedReadService.fetchMenuNameRatingList(imageMenus);

        return FeedMapper.toFeedImageMenuResponses(imageIdUrlList, menuNameRatingList);
    }

}
