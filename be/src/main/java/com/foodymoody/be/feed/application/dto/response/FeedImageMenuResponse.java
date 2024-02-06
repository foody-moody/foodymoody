package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.FeedId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FeedImageMenuResponse {

    private FeedId id;
    private FeedImageResponse image;
    private FeedMenuResponse menu;

    public static FeedImageMenuResponse from(
            FeedId id,
            FeedImageResponse image,
            FeedMenuResponse menu
    ) {
        return new FeedImageMenuResponse(id, image, menu);
    }

}
