package com.foodymoody.be.image.application.dto;

import com.foodymoody.be.common.util.ids.ImageId;
import lombok.Getter;

@Getter
public class ImageDefaultProfileData {

    private ImageId id;
    private String profileImageUrl;

    public ImageDefaultProfileData() {
    }

    private ImageDefaultProfileData(ImageId id, String profileImageUrl) {
        this.id = id;
        this.profileImageUrl = profileImageUrl;
    }

    public static ImageDefaultProfileData of(ImageId id, String profileImageUrl) {
        return new ImageDefaultProfileData(id, profileImageUrl);
    }

}
