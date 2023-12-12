package com.foodymoody.be.feed.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedTasteMoodResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private String name;

    public FeedTasteMoodResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
