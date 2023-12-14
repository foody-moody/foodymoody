package com.foodymoody.be.feed.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedImageResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private String url;

    public FeedImageResponse(String id, String url) {
        this.id = id;
        this.url = url;
    }

}
