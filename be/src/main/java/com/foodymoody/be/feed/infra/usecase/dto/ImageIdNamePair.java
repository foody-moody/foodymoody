package com.foodymoody.be.feed.infra.usecase.dto;

import com.foodymoody.be.common.util.ids.ImageId;
import lombok.Getter;

@Getter
public class ImageIdNamePair {

    private ImageId id;
    private String url;

    public ImageIdNamePair(ImageId id, String url) {
        this.id = id;
        this.url = url;
    }

}
