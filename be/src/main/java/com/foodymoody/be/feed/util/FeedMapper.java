package com.foodymoody.be.feed.util;

import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.domain.ImageMenu;
import com.foodymoody.be.feed.dto.request.FeedRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.dto.request.FeedUpdateRequest;
import com.foodymoody.be.feed.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.dto.response.FeedMenuResponse;
import com.foodymoody.be.feed.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.dto.response.FeedRegisterResponse;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.Menu;
import java.util.List;
import java.util.stream.Collectors;

public class FeedMapper {

    public static Feed toFeed(String id, FeedServiceRegisterRequest request, List<String> moodIds, List<Image> images,
                              List<Menu> menus) {
        return new Feed(id, request.getLocation(), request.getReview(), moodIds, images, menus);
    }

    public static FeedRegisterResponse toFeedRegisterResponse(Feed savedFeed) {
        return new FeedRegisterResponse(savedFeed.getId());
    }

    public static FeedReadResponse toFeedReadResponse(Feed feed, List<FeedImageMenuResponse> images,
                                                      List<String> moodNames) {
        return FeedReadResponse.builder()
                .id(feed.getId())
                .location(feed.getLocation())
                .review(feed.getReview())
                .storeMood(moodNames)
                .images(images)
                .createdAt(feed.getCreatedAt())
                .updatedAt(feed.getUpdatedAt())
                .build();
    }

    public static FeedServiceRegisterRequest toServiceRegisterRequest(FeedRegisterRequest request) {
        return FeedServiceRegisterRequest.builder()
                .location(request.getLocation())
                .review(request.getReview())
                .storeMood(request.getStoreMood())
                .images(request.getImages())
                .build();
    }

    public static FeedServiceUpdateRequest toServiceUpdateRequest(FeedUpdateRequest request) {
        return FeedServiceUpdateRequest.builder()
                .location(request.getLocation())
                .review(request.getReview())
                .storeMood(request.getStoreMood())
                .images(request.getImages())
                .build();
    }

    public static List<FeedImageMenuResponse> toFeedImageMenuResponses(List<ImageMenu> imageMenus) {
        return imageMenus.stream()
                .map(imageMenu -> new FeedImageMenuResponse(imageMenu.getImageId(), imageMenu.getImageUrl(),
                        new FeedMenuResponse(imageMenu.getMenuName(), imageMenu.getRating())))
                .collect(Collectors.toUnmodifiableList());
    }

}
