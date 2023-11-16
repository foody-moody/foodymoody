package com.foodymoody.be.feed.dto.request;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
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

    public FeedRegisterRequestMenu getMenu() {
        return menu;
    }

}
