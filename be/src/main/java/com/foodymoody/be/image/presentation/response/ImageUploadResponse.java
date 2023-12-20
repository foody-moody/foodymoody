package com.foodymoody.be.image.presentation.response;

import com.foodymoody.be.common.util.ids.ImageId;
import lombok.Getter;

@Getter
public class ImageUploadResponse {

    private final ImageId id;
    private final String url;

    public ImageUploadResponse(ImageId id, String url) {
        this.id = id;
        this.url = url;
    }
}

