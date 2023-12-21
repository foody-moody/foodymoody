package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.ImageId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageMenuPair {

    private ImageId imageId;
    private FeedRegisterRequestMenu menu;

    public ImageMenuPair(ImageId imageId, FeedRegisterRequestMenu menu) {
        this.imageId = imageId;
        this.menu = menu;
    }

    public ImageId getImageId() {
        return imageId;
    }

    public void setImageId(ImageId imageId) {
        this.imageId = imageId;
    }

    public FeedRegisterRequestMenu getMenu() {
        return menu;
    }

    public void setMenu(FeedRegisterRequestMenu menu) {
        this.menu = menu;
    }

}
