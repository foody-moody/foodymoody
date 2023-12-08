package com.foodymoody.be.feed.service;

import static com.foodymoody.be.menu.util.MenuMapper.makeMenu;

import com.foodymoody.be.common.exception.FeedIdNotExistsException;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.domain.ImageMenu;
import com.foodymoody.be.feed.domain.StoreMood;
import com.foodymoody.be.feed.domain.StoreMoodId;
import com.foodymoody.be.feed.dto.request.FeedServiceDeleteRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.dto.request.ImageMenuPair;
import com.foodymoody.be.feed.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.dto.response.FeedMemberResponse;
import com.foodymoody.be.feed.dto.response.FeedReadAllResponse;
import com.foodymoody.be.feed.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.dto.response.FeedStoreMoodResponse;
import com.foodymoody.be.feed.dto.response.FeedTasteMoodResponse;
import com.foodymoody.be.feed.repository.FeedRepository;
import com.foodymoody.be.feed.repository.dto.MemberProfileFeedPreviewResponse;
import com.foodymoody.be.feed.service.dto.ImageIdNamePair;
import com.foodymoody.be.feed.service.dto.MenuNameRatingPair;
import com.foodymoody.be.feed.util.FeedMapper;
import com.foodymoody.be.feed_heart_count.domain.FeedHeartCount;
import com.foodymoody.be.feed_heart_count.service.FeedHeartCountService;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.service.ImageService;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.repository.MemberFeedData;
import com.foodymoody.be.member.service.MemberService;
import com.foodymoody.be.menu.domain.Menu;
import com.foodymoody.be.menu.service.MenuService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final ImageService imageService;
    private final MemberService memberService;
    private final MenuService menuService;
    private final StoreMoodService storeMoodService;
    private final FeedHeartCountService feedHeartCountService;

    public Slice<FeedReadAllResponse> readAll(Pageable pageable) {
        Slice<Feed> feeds = feedRepository.findAll(pageable);
        List<FeedReadAllResponse> responses = new ArrayList<>();

        for (Feed feed : feeds) {
            List<FeedImageMenuResponse> images = makeFeedImageMenuResponses(feed);
            List<String> storeMoodIds = feed.getStoreMoodIds();

            FeedReadAllResponse response = FeedReadAllResponse.builder()
                    .id(feed.getId().getValue())
                    .member(makeFeedMemberResponse(feed))
                    .location(feed.getLocation())
                    .review(feed.getReview())
                    .storeMood(makeFeedStoreMoodResponses(storeMoodIds))
                    .images(images)
                    .createdAt(feed.getCreatedAt())
                    .updatedAt(feed.getUpdatedAt())
                    .commentCount(feed.getCommentCount())
                    .isLiked(feed.isLiked())
                    .likeCount(feed.getLikeCount())
                    .build();

            responses.add(response);
        }

        return new SliceImpl<>(responses, pageable, feeds.hasNext());
    }

    public boolean exists(String feedId) {
        return feedRepository.existsById(IdFactory.createFeedId(feedId));
    }

    public void validate(String feedId) {
        if (!exists(feedId)) {
            throw new FeedIdNotExistsException();
        }
    }

    @Transactional
    public FeedRegisterResponse register(FeedServiceRegisterRequest request) {
        Member member = memberService.findById(request.getMemberId());
        MemberId memberId = member.getId();
        List<ImageMenuPair> imageMenuPairs = request.getImages();
        List<Menu> menus = toMenu(imageMenuPairs);
        List<Image> images = toImage(imageMenuPairs, memberId);
        List<String> storeMoodIds = request.getStoreMood();
        String profileImageUrl = imageService.findById(member.getProfileImageId()).getUrl();

        Feed feed = FeedMapper.toFeed(IdFactory.createFeedId(), memberId, request, storeMoodIds, images, menus, profileImageUrl);
        Feed savedFeed = feedRepository.save(feed);

        feedHeartCountService.save(new FeedHeartCount(IdFactory.createFeedHeartCountId(), savedFeed.getId(), 0));

        return FeedMapper.toFeedRegisterResponse(savedFeed);
    }

    public FeedReadResponse read(String id) {
        FeedId feedId = IdFactory.createFeedId(id);
        Feed feed = findFeed(feedId);
        List<FeedImageMenuResponse> images = makeFeedImageMenuResponses(feed);
        List<String> storeMoodIds = feed.getStoreMoodIds();

        FeedMemberResponse feedMemberResponse = makeFeedMemberResponse(feed);

        return FeedMapper.toFeedReadResponse(feedMemberResponse, feed, images,
                makeFeedStoreMoodResponses(storeMoodIds));
    }

    @Transactional
    public void update(String id, FeedServiceUpdateRequest request) {
        FeedId feedId = IdFactory.createFeedId(id);
        Feed feed = findFeed(feedId);
        Member member = memberService.findById(request.getMemberId());
        MemberId memberId = member.getId();
        List<Image> newImages = toImage(request.getImages(), memberId);
        List<Menu> newMenus = toMenu(request.getImages());
        List<String> newStoreMoodIds = request.getStoreMood();
        String profileImageUrl = imageService.findById(member.getProfileImageId()).getUrl();

        feed.update(memberId, request.getLocation(), request.getReview(), newStoreMoodIds, newImages, newMenus, profileImageUrl);
    }

    public Feed findFeed(FeedId id) {
        return feedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 피드가 존재하지 않습니다."));
    }

    @Transactional
    public void delete(FeedServiceDeleteRequest request) {
        FeedId feedId = IdFactory.createFeedId(request.getId());
        MemberId memberId = memberService.findById(request.getMemberId()).getId();

        if (!findFeed(feedId).getMemberId().isSame(memberId)) {
            throw new IllegalArgumentException("이 피드를 작성한 회원이 아닙니다.");
        }

        feedRepository.deleteById(IdFactory.createFeedId(request.getId()));
    }

    public Slice<MemberProfileFeedPreviewResponse> findPreviewsByMemberId(MemberId memberId, Pageable pageable) {
        return feedRepository.fetchPreviewsByMemberId(memberId, pageable);
    }

    private List<FeedStoreMoodResponse> makeFeedStoreMoodResponses(List<String> storeMoodIds) {
        List<StoreMoodId> ids = storeMoodIds.stream()
                .map(StoreMoodId::new)
                .collect(Collectors.toUnmodifiableList());
        List<StoreMood> storeMoods = storeMoodService.findAllById(ids);
        List<FeedStoreMoodResponse> feedStoreMoodResponses = new ArrayList<>();
        for (int i = 0; i < storeMoodIds.size(); i++) {
            feedStoreMoodResponses.add(new FeedStoreMoodResponse(storeMoodIds.get(i), storeMoods.get(i).getName()));
        }

        return feedStoreMoodResponses;
    }

    // REFACTOR: Mapper로 옮기기, service 지우기
    private List<Menu> toMenu(List<ImageMenuPair> imageMenuPairs) {
        return imageMenuPairs.stream()
                .map(imageMenuPair -> menuService.saveMenu(makeMenu(IdFactory.createMenuId(), imageMenuPair.getMenu())))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Image> toImage(List<ImageMenuPair> imageMenuPairs, MemberId memberId) {
        return imageMenuPairs.stream()
                .map(imageMenuPair -> new Image(IdFactory.createImageId(imageMenuPair.getImageId()),
                        findImageUrl(imageMenuPair), memberId))
                .collect(Collectors.toUnmodifiableList());
    }

    private String findImageUrl(ImageMenuPair imageMenuPair) {
        return imageService.findById(imageMenuPair.getImageId()).getUrl();
    }

    // REFACTOR: 쿼리 써서 n + 1 문제 해결 (stream 안에서 service 사용하지 않도록)
    private List<FeedImageMenuResponse> makeFeedImageMenuResponses(Feed feed) {
        List<ImageMenu> imageMenus = feed.getImageMenus();

        List<ImageIdNamePair> imageIdUrlList = findImageIdUrlList(imageMenus);
        List<MenuNameRatingPair> menuNameRatingList = findMenuNameRatingList(imageMenus);

        return FeedMapper.toFeedImageMenuResponses(imageIdUrlList, menuNameRatingList);
    }

    private FeedMemberResponse makeFeedMemberResponse(Feed feed) {
        MemberFeedData memberData = memberService.fetchFeedDataById(feed.getMemberId());
        return toFeedMemberResponse(memberData);
    }

    private FeedMemberResponse toFeedMemberResponse(MemberFeedData member) {
        return FeedMemberResponse.builder()
                .id(member.getId())
                .imageUrl(member.getProfileImageUrl())
                .nickname(member.getNickname())
                .tasteMood(new FeedTasteMoodResponse(member.getId(), member.getMoodName()))
                .build();
    }

    private List<ImageIdNamePair> findImageIdUrlList(List<ImageMenu> imageMenus) {
        return imageMenus.stream()
                .map(imageMenu -> {
                    Image image = imageService.findById(imageMenu.getImageId());
                    return new ImageIdNamePair(image.getId().getValue(), image.getUrl());
                })
                .collect(Collectors.toUnmodifiableList());
    }

    private List<MenuNameRatingPair> findMenuNameRatingList(List<ImageMenu> imageMenuList) {
        return imageMenuList.stream()
                .map(imageMenu -> {
                    Menu menu = menuService.findBy(imageMenu.getMenuId());
                    return new MenuNameRatingPair(menu.getName(), menu.getRating());
                })
                .collect(Collectors.toUnmodifiableList());
    }

}
