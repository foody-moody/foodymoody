package com.foodymoody.be.feed.application.dto.request;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageMenuPair {

    private String imageId;
    private FeedRegisterRequestMenu menu;

    public ImageMenuPair(String imageId, FeedRegisterRequestMenu menu) {
        this.imageId = imageId;
        this.menu = menu;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public FeedRegisterRequestMenu getMenu() {
        return menu;
    }

    public void setMenu(FeedRegisterRequestMenu menu) {
        this.menu = menu;
    }

}
