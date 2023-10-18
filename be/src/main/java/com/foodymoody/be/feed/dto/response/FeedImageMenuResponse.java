package com.foodymoody.be.feed.dto.response;

public class FeedImageMenuResponse {

    private String imageUrl;
    private FeedMenuResponse menu;

    public FeedImageMenuResponse(String imageUrl, FeedMenuResponse menu) {
        this.imageUrl = imageUrl;
        this.menu = menu;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public FeedMenuResponse getMenu() {
        return menu;
    }

}
