package com.foodymoody.be.feed_heart.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedHeartResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private String feedId;
    @JsonProperty
    private String memberId;
    @JsonProperty
    private boolean isLiked;
    @JsonProperty
    private int feedHeartCount;

    public FeedHeartResponse(String id, String feedId, String memberId, boolean isLiked, int feedHeartCount) {
        this.id = id;
        this.feedId = feedId;
        this.memberId = memberId;
        this.isLiked = isLiked;
        this.feedHeartCount = feedHeartCount;
    }

}
