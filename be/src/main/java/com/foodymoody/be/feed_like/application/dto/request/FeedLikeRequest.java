package com.foodymoody.be.feed_like.application.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class FeedLikeRequest {

    private String feedId;

    public FeedLikeRequest(String feedId) {
        this.feedId = feedId;
    }

}
