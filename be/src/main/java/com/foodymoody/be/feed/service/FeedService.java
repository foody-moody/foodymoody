package com.foodymoody.be.feed.service;

import com.foodymoody.be.common.exception.FeedIdNotExistsException;
import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.domain.ImageMenu;
import com.foodymoody.be.feed.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.dto.request.ImageMenuPair;
import com.foodymoody.be.feed.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.dto.response.FeedReadAllResponse;
import com.foodymoody.be.feed.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.dto.response.FeedStoreMoodResponse;
import com.foodymoody.be.feed.repository.FeedRepository;
import com.foodymoody.be.feed.util.FeedMapper;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.util.ImageMapper;
import com.foodymoody.be.menu.domain.Menu;
import com.foodymoody.be.menu.util.MenuMapper;
import com.foodymoody.be.mood.domain.Mood;
import com.foodymoody.be.mood.repository.MoodRepository;
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
    private final MoodRepository moodRepository;

    public Slice<FeedReadAllResponse> readAll(Pageable pageable) {
        Slice<Feed> feeds = feedRepository.findAll(pageable);
        List<FeedReadAllResponse> responses = new ArrayList<>();

        for (Feed feed : feeds) {
            List<FeedImageMenuResponse> images = getFeedImageMenuResponses(feed);
            List<String> storeMoodIds = feed.getStoreMoodIds();

            FeedReadAllResponse response = FeedReadAllResponse.builder()
                    .id(feed.getId())
                    // TODO: 회원 정보 로직 구현 후 추가
                    // MEMBER 객체로 주입하지 말고, DTO로 주입 (그 정보만)
                    .member(null)
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
    private List<String> findMoodIds(List<String> storeMoodNames) {
        return storeMoodNames.stream()
                .map(this::findMoodByName)
                .map(Mood::getId)
                .collect(Collectors.toUnmodifiableList());
    }

    // TODO: JPA에서 제공하는 메서드인지 찾아보기 (속도 개선)
    public List<String> findMoodNames(List<String> moodIds) {
        return moodIds.stream()
                .map(this::findMoodById)
                .map(Mood::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public Mood findMoodByName(String s) {
        return moodRepository.findByName(s)
                .orElseThrow(() -> new IllegalArgumentException("Mood를 찾을 수 없습니다."));
    }

    public Mood findMoodById(String id) {
        return moodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mood를 찾을 수 없습니다."));
    }

    public FeedReadResponse read(String id) {
        Feed feed = findFeed(id);
        List<FeedImageMenuResponse> images = getFeedImageMenuResponses(feed);

        List<String> storeMoodIds = feed.getStoreMoodIds();
        return FeedMapper.toFeedReadResponse(feed, images, makeFeedStoreMoodResponses(storeMoodIds));
    }

    @Transactional
    public void update(String id, FeedServiceUpdateRequest request) {
        Feed feed = findFeed(id);

        List<Image> newImages = ImageMapper.toImage(request.getImages());
        List<Menu> newMenus = MenuMapper.toMenu(request.getImages());

        List<String> newStoreMoodIds = request.getStoreMood();

        feed.update(request.getLocation(), request.getReview(), newStoreMoodIds, newImages, newMenus);
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
