package com.foodymoody.be.feed.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedTasteMoodResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private String mood;

    public FeedTasteMoodResponse(String id, String mood) {
        this.id = id;
        this.mood = mood;
    }

}
