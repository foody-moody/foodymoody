package com.foodymoody.be.feed.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedStoreMoodResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private String name;

    public FeedStoreMoodResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
