package com.foodymoody.be.feed_heart.application.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class FeedHeartRequest {

    private String feedId;

    public FeedHeartRequest(String feedId) {
        this.feedId = feedId;
    }

}
