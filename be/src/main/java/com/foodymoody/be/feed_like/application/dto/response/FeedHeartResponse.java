package com.foodymoody.be.feed_like.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedHeartResponse {

    @Getter
    private String id;
    @Getter
    private String feedId;
    @Getter
    private String memberId;
    @JsonProperty
    private boolean isLiked;
    @Getter
    private int feedHeartCount;

    public FeedHeartResponse(String id, String feedId, String memberId, boolean isLiked, int feedHeartCount) {
        this.id = id;
        this.feedId = feedId;
        this.memberId = memberId;
        this.isLiked = isLiked;
        this.feedHeartCount = feedHeartCount;
    }

}
