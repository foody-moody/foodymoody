package com.foodymoody.be.feed.util;

import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.dto.request.FeedRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.dto.request.FeedUpdateRequest;
import com.foodymoody.be.feed.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.dto.response.FeedMemberResponse;
import com.foodymoody.be.feed.dto.response.FeedMenuResponse;
import com.foodymoody.be.feed.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.dto.response.FeedStoreMoodResponse;
import com.foodymoody.be.feed.service.dto.ImageIdNamePair;
import com.foodymoody.be.feed.service.dto.MenuNameRatingPair;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.Menu;
import java.util.ArrayList;
import java.util.List;

public class FeedMapper {

    private FeedMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Feed toFeed(String id, String memberId, FeedServiceRegisterRequest request, List<String> moodIds,
            List<Image> images,
            List<Menu> menus) {
        return new Feed(id, memberId, request.getLocation(), request.getReview(), moodIds, images, menus);
    }

    public static FeedRegisterResponse toFeedRegisterResponse(Feed savedFeed) {
        return new FeedRegisterResponse(savedFeed.getId());
    }

    public static FeedReadResponse toFeedReadResponse(FeedMemberResponse feedMemberResponse, Feed feed,
            List<FeedImageMenuResponse> images,
            List<FeedStoreMoodResponse> moodNames) {
        return FeedReadResponse.builder()
                .id(feed.getId())
                .member(feedMemberResponse)
                .location(feed.getLocation())
                .review(feed.getReview())
                .storeMood(moodNames)
                .images(images)
                .createdAt(feed.getCreatedAt())
                .updatedAt(feed.getUpdatedAt())
                .build();
    }

    public static FeedServiceRegisterRequest toServiceRegisterRequest(FeedRegisterRequest request, String memberId) {
        return FeedServiceRegisterRequest.builder()
                .memberId(memberId)
                .location(request.getLocation())
                .review(request.getReview())
                .storeMood(request.getStoreMood())
                .images(request.getImages())
                .build();
    }

    public static FeedServiceUpdateRequest toServiceUpdateRequest(FeedUpdateRequest request, String memberId) {
        return FeedServiceUpdateRequest.builder()
                .memberId(memberId)
                .location(request.getLocation())
                .review(request.getReview())
                .storeMood(request.getStoreMood())
                .images(request.getImages())
                .build();
    }

    public static List<FeedImageMenuResponse> toFeedImageMenuResponses(List<ImageIdNamePair> imageIdUrlList, List<MenuNameRatingPair> menuNameRatingList) {
        List<FeedImageMenuResponse> feedImageMenuResponses = new ArrayList<>();
        for (int i = 0; i < imageIdUrlList.size(); i++) {
            feedImageMenuResponses.add(
                    new FeedImageMenuResponse(imageIdUrlList.get(i).getId(), imageIdUrlList.get(i).getUrl(),
                            new FeedMenuResponse(menuNameRatingList.get(i).getName(), menuNameRatingList.get(i).getRating())));
        }

        return feedImageMenuResponses;
    }

}
