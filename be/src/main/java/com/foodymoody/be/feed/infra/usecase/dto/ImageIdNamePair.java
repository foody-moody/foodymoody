package com.foodymoody.be.feed.infra.usecase.dto;

import com.foodymoody.be.common.util.ids.ImageId;

public class ImageIdNamePair {

    private ImageId id;
    private String url;

    public ImageIdNamePair(ImageId id, String url) {
        this.id = id;
        this.url = url;
    }

    public ImageId getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

}
