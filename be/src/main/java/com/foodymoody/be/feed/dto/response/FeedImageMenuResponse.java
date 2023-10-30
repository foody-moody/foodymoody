package com.foodymoody.be.feed.dto.response;

public class FeedImageMenuResponse {

    private String id;
    private String imageUrl;
    private FeedMenuResponse menu;

    public FeedImageMenuResponse(String id, String imageUrl, FeedMenuResponse menu) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.menu = menu;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public FeedMenuResponse getMenu() {
        return menu;
    }

}
