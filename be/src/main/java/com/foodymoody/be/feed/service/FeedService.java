package com.foodymoody.be.feed.service;

import com.foodymoody.be.feed.dto.FeedRegisterRequest;
import com.foodymoody.be.feed.dto.FeedRegisterResponse;
import com.foodymoody.be.feed.entity.Feed;
import com.foodymoody.be.feed.entity.Menu;
import com.foodymoody.be.feed.mapper.MenuMapper;
import com.foodymoody.be.feed.repository.FeedRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;

    public FeedRegisterResponse register(FeedRegisterRequest request) {
        List<Menu> menus = MenuMapper.toMenu(request.getMenus());

        Feed feed = new Feed(request.getReview(), request.getImages(), menus);
        return null;
    }
}
