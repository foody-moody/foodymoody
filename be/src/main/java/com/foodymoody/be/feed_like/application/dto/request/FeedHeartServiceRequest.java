package com.foodymoody.be.feed_like.application.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FeedHeartServiceRequest {

    private String feedId;
    private String memberId;

    public FeedHeartServiceRequest(String feedId, String memberId) {
        this.feedId = feedId;
        this.memberId = memberId;
    }

}
