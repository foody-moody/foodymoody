package com.foodymoody.be.feed.service;

import com.foodymoody.be.common.exception.FeedIdNotExistsException;
import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.domain.ImageMenu;
import com.foodymoody.be.feed.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.dto.request.ImageMenuPair;
import com.foodymoody.be.feed.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.dto.response.FeedMemberResponse;
import com.foodymoody.be.feed.dto.response.FeedReadAllResponse;
import com.foodymoody.be.feed.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.dto.response.FeedStoreMoodResponse;
import com.foodymoody.be.feed.repository.FeedRepository;
import com.foodymoody.be.feed.util.FeedMapper;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.util.ImageMapper;
import com.foodymoody.be.member.repository.MemberFeedData;
import com.foodymoody.be.member.service.MemberService;
import com.foodymoody.be.menu.domain.Menu;
import com.foodymoody.be.menu.util.MenuMapper;
import com.foodymoody.be.mood.domain.Mood;
import com.foodymoody.be.mood.service.MoodService;
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
    private final MoodService moodService;
    private final MemberService memberService;

    public Slice<FeedReadAllResponse> readAll(Pageable pageable) {
        Slice<Feed> feeds = feedRepository.findAll(pageable);
        List<FeedReadAllResponse> responses = new ArrayList<>();

        for (Feed feed : feeds) {
            List<FeedImageMenuResponse> images = getFeedImageMenuResponses(feed);
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
                    // TODO: 아래 로직 구현 후 추가
                    .likeCount(feed.getLikeCount())
                    .isLiked(feed.isLiked())
                    .commentCount(feed.getCommentCount())
                    .build();

            responses.add(response);
        }

        return new SliceImpl<>(responses, pageable, feeds.hasNext());
    }

    private List<FeedStoreMoodResponse> makeFeedStoreMoodResponses(List<String> storeMoodIds) {
        List<String> storeMoodNames = findMoodNames(storeMoodIds);
        List<FeedStoreMoodResponse> feedStoreMoodResponses = new ArrayList<>();
        for (int i = 0; i < storeMoodIds.size(); i++) {
            feedStoreMoodResponses.add(new FeedStoreMoodResponse(storeMoodIds.get(i), storeMoodNames.get(i)));
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
        List<Menu> menus = MenuMapper.toMenu(imageMenuPairs);
        List<Image> images = ImageMapper.toImage(imageMenuPairs);
        List<String> storeMoodIds = request.getStoreMood();

        Feed feed = FeedMapper.toFeed(IdGenerator.generate(), memberId, request, storeMoodIds, images, menus);

        return FeedMapper.toFeedRegisterResponse(feedRepository.save(feed));
    }

    // TODO: JPA에서 제공하는 메서드인지 찾아보기 (속도 개선)
    public List<String> findMoodNames(List<String> moodIds) {
        return moodIds.stream()
                .map(moodService::findMoodById)
                .map(Mood::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public FeedReadResponse read(String id) {
        Feed feed = findFeed(id);
        List<FeedImageMenuResponse> images = getFeedImageMenuResponses(feed);
        List<String> storeMoodIds = feed.getStoreMoodIds();

        FeedMemberResponse feedMemberResponse = makeFeedMemberResponse(feed);

        return FeedMapper.toFeedReadResponse(feedMemberResponse, feed, images,
                makeFeedStoreMoodResponses(storeMoodIds));
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
                // TODO: member의 mood가 moodId로 수정되면 주석 풀기
//                .tasteMood(new FeedTasteMoodResponse(member.getMoodId(), moodName))
                .build();
    }

    @Transactional
    public void update(String id, FeedServiceUpdateRequest request) {
        Feed feed = findFeed(id);

        String memberId = request.getMemberId();
        List<Image> newImages = ImageMapper.toImage(request.getImages());
        List<Menu> newMenus = MenuMapper.toMenu(request.getImages());
        List<String> newStoreMoodIds = request.getStoreMood();

        feed.update(memberId, request.getLocation(), request.getReview(), newStoreMoodIds, newImages, newMenus);
    }

    @Transactional
    public void delete(String id) {
        feedRepository.deleteById(id);
    }

    private List<FeedImageMenuResponse> getFeedImageMenuResponses(Feed feed) {
        List<ImageMenu> imageMenus = feed.getImageMenus();
        return FeedMapper.toFeedImageMenuResponses(imageMenus);
    }

    private Feed findFeed(String id) {
        return feedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 피드가 존재하지 않습니다."));
    }

}
