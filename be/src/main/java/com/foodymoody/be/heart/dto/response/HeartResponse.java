package com.foodymoody.be.heart.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeartResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private boolean isLiked;
    @JsonProperty
    private String feedId;
    @JsonProperty
    private String memberId;

    public HeartResponse(String id, boolean isLiked, String feedId, String memberId) {
        this.id = id;
        this.isLiked = isLiked;
        this.feedId = feedId;
        this.memberId = memberId;
    }

}
