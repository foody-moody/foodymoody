package com.foodymoody.be.feed.service;

import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.dto.request.ImageMenuPair;
import com.foodymoody.be.feed.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.dto.response.FeedMenuResponse;
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

        List<Image> feedImages = feed.getImages();
        List<Menu> feedMenus = feed.getMenus();

        List<FeedImageMenuResponse> images = new ArrayList<>();
        if (feedImages.size() != feedMenus.size()) {
            throw new IllegalArgumentException("피드의 이미지와 메뉴의 개수가 다릅니다.");
        }
        for (int i = 0; i < feedImages.size(); i++) {
            Image image = feedImages.get(i);
            Menu menu = feedMenus.get(i);
            images.add(
                    new FeedImageMenuResponse(image.getUrl(), new FeedMenuResponse(menu.getName(), menu.getNumStar())));
        }

        return FeedReadResponse.builder()
                .id(id)
                .location(location)
                .review(review)
                .mood(mood)
                .images(images)
                .build();
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
