package com.foodymoody.be.feed.service;

import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.dto.request.ImageMenuPair;
import com.foodymoody.be.feed.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.dto.response.FeedMenuResponse;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FeedService {

    private final FeedRepository feedRepository;

    public List<FeedReadAllResponse> readAll() {
        List<Feed> feeds = feedRepository.findAll();
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
                    .commentCount(0)
                    .build();

            responses.add(response);
        }

        return responses;
    }

    private List<FeedImageMenuResponse> getFeedImageMenuResponses(Feed feed) {
        List<Image> feedImages = feed.getImages();
        List<Menu> feedMenus = feed.getMenus();
        return makeFeedImageMenuResponses(feedImages,
                feedMenus);
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
        String location = feed.getLocation();
        String review = feed.getReview();
        String mood = feed.getMood();

        List<FeedImageMenuResponse> images = getFeedImageMenuResponses(feed);

        return FeedReadResponse.builder()
                .id(id)
                .location(location)
                .review(review)
                .mood(mood)
                .images(images)
                .build();
    }

    private List<FeedImageMenuResponse> makeFeedImageMenuResponses(List<Image> feedImages, List<Menu> feedMenus) {
        validateFeedOfImagesAndMenus(feedImages, feedMenus);

        List<FeedImageMenuResponse> images = new ArrayList<>();
        for (int i = 0; i < feedImages.size(); i++) {
            Image image = feedImages.get(i);
            Menu menu = feedMenus.get(i);
            images.add(
                    new FeedImageMenuResponse(image.getUrl(), new FeedMenuResponse(menu.getName(), menu.getNumStar())));
        }

        return images;
    }

    private void validateFeedOfImagesAndMenus(List<Image> feedImages, List<Menu> feedMenus) {
        if (feedImages.size() != feedMenus.size()) {
            throw new IllegalArgumentException("피드의 이미지와 메뉴의 개수가 다릅니다.");
        }
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
