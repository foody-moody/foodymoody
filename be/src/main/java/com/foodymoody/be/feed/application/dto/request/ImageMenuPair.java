package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.ImageId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ImageMenuPair {

    private ImageId imageId;
    private FeedRegisterRequestMenu menu;

    public ImageMenuPair(ImageId imageId, FeedRegisterRequestMenu menu) {
        this.imageId = imageId;
        this.menu = menu;
    }

}
