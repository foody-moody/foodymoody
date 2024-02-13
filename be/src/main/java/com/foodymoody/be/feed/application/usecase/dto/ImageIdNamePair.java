package com.foodymoody.be.feed.application.usecase.dto;

import com.foodymoody.be.common.util.ids.ImageId;
import lombok.Getter;

@Getter
public class ImageIdNamePair {

    private ImageId id;
    private String url;

    /**
     * FeedJpaRepository에서 사용 중
     */
    public ImageIdNamePair(ImageId id, String url) {
        this.id = id;
        this.url = url;
    }

}
