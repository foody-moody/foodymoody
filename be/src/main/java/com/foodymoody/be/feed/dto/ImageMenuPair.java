package com.foodymoody.be.feed.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageMenuPair {

    private String imageUrl;
    private FeedRegisterRequestMenu menu;

    @Builder
    public ImageMenuPair(String imageUrl, FeedRegisterRequestMenu menu) {
        this.imageUrl = imageUrl;
        this.menu = menu;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public FeedRegisterRequestMenu getMenu() {
        return menu;
    }

}
