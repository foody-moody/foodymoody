package com.foodymoody.be.image.presentation.response;

import lombok.Getter;

@Getter
public class ImageUploadResponse {

    private final String id;
    private final String url;

    public ImageUploadResponse(String id, String url) {
        this.id = id;
        this.url = url;
    }
}

