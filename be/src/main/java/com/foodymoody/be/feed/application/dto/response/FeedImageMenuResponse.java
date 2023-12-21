package com.foodymoody.be.feed.application.dto.response;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedImageMenuResponse {

    private String id;
    private FeedImageResponse image;
    private FeedMenuResponse menu;

    public FeedImageMenuResponse(String id, FeedImageResponse image, FeedMenuResponse menu) {
        this.id = id;
        this.image = image;
        this.menu = menu;
    }

    public String getId() {
        return id;
    }

    public FeedImageResponse getImage() {
        return image;
    }

    public FeedMenuResponse getMenu() {
        return menu;
    }

}
