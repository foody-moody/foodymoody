package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.FeedId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FeedImageMenuResponse {

    private FeedId id;
    private FeedImageResponse image;
    private FeedMenuResponse menu;

    public FeedImageMenuResponse(
            FeedId id,
            FeedImageResponse image,
            FeedMenuResponse menu
    ) {
        this.id = id;
        this.image = image;
        this.menu = menu;
    }

}
