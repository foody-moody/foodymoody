package com.foodymoody.be.feed.util;

import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.dto.request.FeedRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.dto.response.FeedRegisterResponse;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.Menu;
import java.util.List;

public class FeedMapper {

    public static Feed toFeed(FeedServiceRegisterRequest request, List<Image> images, List<Menu> menus) {
        return new Feed(request.getLocation(), request.getReview(), request.getMood(), images, menus);
    }

    public static FeedRegisterResponse toFeedRegisterResponse(Feed savedFeed) {
        return new FeedRegisterResponse(savedFeed.getId());
    }

    public static FeedServiceRegisterRequest toServiceRegisterRequest(FeedRegisterRequest feedRegisterRequest) {
        return FeedServiceRegisterRequest.builder()
                .location(feedRegisterRequest.getLocation())
                .review(feedRegisterRequest.getReview())
                .mood(feedRegisterRequest.getMood())
                .images(feedRegisterRequest.getImages())
                .build();
    }
}
