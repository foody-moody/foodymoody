package com.foodymoody.be.feed.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedRegisterRequest {

    private String review;
    private List<String> imageUrls;
    private List<FeedRegisterRequestMenu> menus;

    @Builder
    public FeedRegisterRequest(String review, List<String> imageUrls, List<FeedRegisterRequestMenu> menus) {
        this.review = review;
        this.imageUrls = imageUrls;
        this.menus = menus;
    }

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
