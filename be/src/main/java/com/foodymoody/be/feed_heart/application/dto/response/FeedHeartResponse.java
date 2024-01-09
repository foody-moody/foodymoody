package com.foodymoody.be.feed_heart.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedHeartResponse {

    private String id;
    private String feedId;
    private String memberId;
    @JsonProperty
    private boolean isLiked;
    private int feedHeartCount;

    public FeedHeartResponse(String id, String feedId, String memberId, boolean isLiked, int feedHeartCount) {
        this.id = id;
        this.feedId = feedId;
        this.memberId = memberId;
        this.isLiked = isLiked;
        this.feedHeartCount = feedHeartCount;
    }

    public String getId() {
        return id;
    }

    public String getFeedId() {
        return feedId;
    }

    public String getMemberId() {
        return memberId;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public int getFeedHeartCount() {
        return feedHeartCount;
    }

}
