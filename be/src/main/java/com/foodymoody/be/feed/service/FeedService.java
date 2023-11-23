package com.foodymoody.be.feed.service;

import static com.foodymoody.be.menu.util.MenuMapper.makeMenu;

import com.foodymoody.be.common.exception.FeedIdNotExistsException;
import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.domain.ImageMenu;
import com.foodymoody.be.feed.domain.StoreMood;
import com.foodymoody.be.feed.domain.StoreMoodId;
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
import com.foodymoody.be.feed.service.dto.ImageIdNamePair;
import com.foodymoody.be.feed.service.dto.MenuNameRatingPair;
import com.foodymoody.be.feed.util.FeedMapper;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.service.ImageService;
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

    public Slice<FeedReadAllResponse> readAll(Pageable pageable) {
        Slice<Feed> feeds = feedRepository.findAll(pageable);
        List<FeedReadAllResponse> responses = new ArrayList<>();

        for (Feed feed : feeds) {
            List<FeedImageMenuResponse> images = makeFeedImageMenuResponses(feed);
            List<String> storeMoodIds = feed.getStoreMoodIds();

            FeedReadAllResponse response = FeedReadAllResponse.builder()
                    .id(feed.getId())
                    .member(makeFeedMemberResponse(feed))
                    .location(feed.getLocation())
                    .review(feed.getReview())
                    .storeMood(makeFeedStoreMoodResponses(storeMoodIds))
                    .images(images)
                    .createdAt(feed.getCreatedAt())
                    .updatedAt(feed.getUpdatedAt())
                    .commentCount(feed.getCommentCount())
                    // TODO: 아래 로직 구현 후 추가
                    .isLiked(feed.isLiked())
                    .likeCount(feed.getLikeCount())
                    .build();

            responses.add(response);
        }

        return new SliceImpl<>(responses, pageable, feeds.hasNext());
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

    public boolean exists(String feedId) {
        return feedRepository.existsById(feedId);
    }

    public void validate(String feedId) {
        if (!exists(feedId)) {
            throw new FeedIdNotExistsException();
        }
    }

    @Transactional
    public FeedRegisterResponse register(FeedServiceRegisterRequest request) {
        String memberId = request.getMemberId();
        List<ImageMenuPair> imageMenuPairs = request.getImages();
        List<Menu> menus = toMenu(imageMenuPairs);
        List<Image> images = toImage(imageMenuPairs);
        List<String> storeMoodIds = request.getStoreMood();

        Feed feed = FeedMapper.toFeed(IdGenerator.generate(), memberId, request, storeMoodIds, images, menus);

        return FeedMapper.toFeedRegisterResponse(feedRepository.save(feed));
    }

    private List<Menu> toMenu(List<ImageMenuPair> imageMenuPairs) {
        return imageMenuPairs.stream()
                .map(imageMenuPair -> menuService.saveMenu(makeMenu(IdGenerator.generate(), imageMenuPair.getMenu())))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Image> toImage(List<ImageMenuPair> imageMenuPairs) {
        return imageMenuPairs.stream()
                .map(imageMenuPair -> new Image(imageMenuPair.getImageId(), findImageUrl(imageMenuPair)))
                .collect(Collectors.toUnmodifiableList());
    }

    private String findImageUrl(ImageMenuPair imageMenuPair) {
        return imageService.findBy(imageMenuPair.getImageId()).getUrl();
    }

    public FeedReadResponse read(String id) {
        Feed feed = findFeed(id);
        List<FeedImageMenuResponse> images = makeFeedImageMenuResponses(feed);
        List<String> storeMoodIds = feed.getStoreMoodIds();

        FeedMemberResponse feedMemberResponse = makeFeedMemberResponse(feed);

        return FeedMapper.toFeedReadResponse(feedMemberResponse, feed, images,
                makeFeedStoreMoodResponses(storeMoodIds));
    }

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
        MemberFeedData memberData = memberService.fetchFeedDataById(member.getId());
        return FeedMemberResponse.builder()
                .id(member.getId())
                .imageUrl(memberData.getProfileImageUrl())
                .nickname(member.getNickname())
                .tasteMood(new FeedTasteMoodResponse(member.getId(), member.getMoodName()))
                .build();
    }

    @Transactional
    public void update(String id, FeedServiceUpdateRequest request) {
        Feed feed = findFeed(id);

        String memberId = request.getMemberId();
        List<Image> newImages = toImage(request.getImages());
        List<Menu> newMenus = toMenu(request.getImages());
        List<String> newStoreMoodIds = request.getStoreMood();

        feed.update(memberId, request.getLocation(), request.getReview(), newStoreMoodIds, newImages, newMenus);
    }

    private Feed findFeed(String id) {
        return feedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 피드가 존재하지 않습니다."));
    }

    @Transactional
    public void delete(String id) {
        feedRepository.deleteById(id);
    }

    private List<ImageIdNamePair> findImageIdUrlList(List<ImageMenu> imageMenus) {
        return imageMenus.stream()
                .map(imageMenu -> {
                    Image image = imageService.findBy(imageMenu.getImageId());
                    return new ImageIdNamePair(image.getId(), image.getUrl());
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
