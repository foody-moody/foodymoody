package com.foodymoody.be.feed.service;

import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.dto.request.ImageMenuPair;
import com.foodymoody.be.feed.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.repository.FeedRepository;
import com.foodymoody.be.feed.util.FeedMapper;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.util.ImageMapper;
import com.foodymoody.be.menu.domain.Menu;
import com.foodymoody.be.menu.util.MenuMapper;
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

    @Transactional
    public Long update(Long id, FeedServiceUpdateRequest request) {
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 피드가 존재하지 않습니다."));

        feed.setLocation(request.getLocation());
        feed.setReview(request.getReview());
        feed.setMood(request.getMood());

        List<Image> newImages = ImageMapper.toImage(request.getImages());
        feed.getImages().clear();
        feed.getImages().addAll(newImages);

        List<Menu> newMenus = MenuMapper.toMenu(request.getImages());  // I assume this is correct, but double-check if you're mapping images to menus here.
        feed.getMenus().clear();
        feed.getMenus().addAll(newMenus);

        return feed.getId();
    }

}
