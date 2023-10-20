package com.foodymoody.be.feed.util;

import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.dto.request.FeedRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.dto.request.FeedUpdateRequest;
import com.foodymoody.be.feed.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.dto.response.FeedReadResponse;
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

    public static FeedReadResponse toFeedReadResponse(Feed feed, List<FeedImageMenuResponse> images) {
        return FeedReadResponse.builder()
                .id(feed.getId())
                .location(feed.getLocation())
                .review(feed.getReview())
                .mood(feed.getMood())
                .images(images)
                .build();
    }

    public static FeedServiceRegisterRequest toServiceRegisterRequest(FeedRegisterRequest request) {
        return FeedServiceRegisterRequest.builder()
                .location(request.getLocation())
                .review(request.getReview())
                .mood(request.getMood())
                .images(request.getImages())
                .build();
    }

    public static FeedServiceUpdateRequest toServiceUpdateRequest(FeedUpdateRequest request) {
        return FeedServiceUpdateRequest.builder()
                .location(request.getLocation())
                .review(request.getReview())
                .mood(request.getMood())
                .images(request.getImages())
                .build();
    }

}
