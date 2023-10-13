package com.foodymoody.be.feed.dto;

import java.util.List;

public class FeedRegisterRequest {

    private String review;
    private List<String> imageUrls;
    private List<FeedRegisterRequestMenu> menus;

    public String getReview() {
        return review;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public List<FeedRegisterRequestMenu> getMenus() {
        return menus;
    }
}
