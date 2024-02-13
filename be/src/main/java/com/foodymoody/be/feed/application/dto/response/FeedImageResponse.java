package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.ImageId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FeedImageResponse {

    private ImageId id;
    private String url;

    public static FeedImageResponse from(ImageId id, String url) {
        return new FeedImageResponse(id, url);
    }

}
