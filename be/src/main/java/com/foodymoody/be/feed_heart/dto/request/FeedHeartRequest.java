package com.foodymoody.be.feed_heart.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedHeartRequest {

    private String feedId;

    public String getFeedId() {
        return feedId;
    }

}
