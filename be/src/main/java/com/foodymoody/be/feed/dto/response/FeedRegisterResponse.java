package com.foodymoody.be.feed.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedRegisterResponse {

    @JsonProperty
    private final String id;

    public FeedRegisterResponse(String id) {
        this.id = id;
    }

}
