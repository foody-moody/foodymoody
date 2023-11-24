package com.foodymoody.be.heart.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeartResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private String feedId;
    @JsonProperty
    private String memberId;

    public HeartResponse(String id, String feedId, String memberId) {
        this.id = id;
        this.feedId = feedId;
        this.memberId = memberId;
    }

}
