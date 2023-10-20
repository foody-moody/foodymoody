package com.foodymoody.be.feed.service;

import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.dto.request.ImageMenuPair;
import com.foodymoody.be.feed.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.dto.response.FeedReadAllResponse;
import com.foodymoody.be.feed.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.repository.FeedRepository;
import com.foodymoody.be.feed.util.FeedMapper;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.util.ImageMapper;
import com.foodymoody.be.menu.domain.Menu;
import com.foodymoody.be.menu.util.MenuMapper;
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

    public Slice<FeedReadAllResponse> readAll(Pageable pageable) {
        Slice<Feed> feeds = feedRepository.findAll(pageable);
        List<FeedReadAllResponse> responses = new ArrayList<>();

        for (Feed feed : feeds) {
            List<FeedImageMenuResponse> images = getFeedImageMenuResponses(feed);

            FeedReadAllResponse response = FeedReadAllResponse.builder()
                    .id(feed.getId())
                    // TODO: 회원 정보 로직 구현 후 추가
                    .member(null)
                    .location(feed.getLocation())
                    .review(feed.getReview())
                    .mood(feed.getMood())
                    .images(images)
                    // TODO: Count 로직 구현 후 추가
                    .likeCount(0)
                    .isLiked(false)
                    .commentCount(0)
                    .build();

            responses.add(response);
        }

        return new SliceImpl<>(responses, pageable, feeds.hasNext());
    }

    private List<FeedImageMenuResponse> getFeedImageMenuResponses(Feed feed) {
        List<Image> feedImages = feed.getImages();
        List<Menu> feedMenus = feed.getMenus();
        return FeedMapper.toFeedImageMenuResponses(feedImages, feedMenus);
    }

    @Transactional
    public FeedRegisterResponse register(FeedServiceRegisterRequest request) {
        List<ImageMenuPair> imageMenuPairs = request.getImages();
        List<Menu> menus = MenuMapper.toMenu(imageMenuPairs);
        List<Image> images = ImageMapper.toImage(imageMenuPairs);

        Feed feed = FeedMapper.toFeed(request, images, menus);

        return FeedMapper.toFeedRegisterResponse(feedRepository.save(feed));
    }

    public FeedReadResponse read(Long id) {
        Feed feed = findFeed(id);
        List<FeedImageMenuResponse> images = getFeedImageMenuResponses(feed);

        return FeedMapper.toFeedReadResponse(feed, images);
    }

    @Transactional
    public void update(Long id, FeedServiceUpdateRequest request) {
        Feed feed = findFeed(id);

        List<Image> newImages = ImageMapper.toImage(request.getImages());
        List<Menu> newMenus = MenuMapper.toMenu(request.getImages());

        feed.update(request.getLocation(), request.getReview(), request.getMood(), newImages, newMenus);
    }

    private Feed findFeed(Long id) {
        return feedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 피드가 존재하지 않습니다."));
    }

    @Transactional
    public void delete(Long id) {
        feedRepository.deleteById(id);
    }

}
