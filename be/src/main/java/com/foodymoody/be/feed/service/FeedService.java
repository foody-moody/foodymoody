package com.foodymoody.be.feed.service;

import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.dto.FeedRegisterRequest;
import com.foodymoody.be.feed.dto.FeedRegisterRequestMenu;
import com.foodymoody.be.feed.dto.FeedRegisterResponse;
import com.foodymoody.be.feed.dto.ImageMenuPair;
import com.foodymoody.be.feed.repository.FeedRepository;
import com.foodymoody.be.feed.util.FeedMapper;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.util.ImageMapper;
import com.foodymoody.be.menu.domain.Menu;
import com.foodymoody.be.menu.util.MenuMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;

    public FeedRegisterResponse register(FeedRegisterRequest request) {
        List<ImageMenuPair> imageMenuPairs = request.getImages();
        List<Menu> menus = MenuMapper.toMenu(imageMenuPairs);
        List<Image> images = ImageMapper.toImage(imageMenuPairs);

        Feed feed = FeedMapper.toFeed(request, images, menus);

        return FeedMapper.toFeedRegisterResponse(feedRepository.save(feed));
    }

}
