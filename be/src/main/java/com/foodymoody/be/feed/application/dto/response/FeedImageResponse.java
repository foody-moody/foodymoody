package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.ImageId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FeedImageResponse {

    private ImageId id;
    private String url;

    public FeedImageResponse(ImageId id, String url) {
        this.id = id;
        this.url = url;
    }

}
