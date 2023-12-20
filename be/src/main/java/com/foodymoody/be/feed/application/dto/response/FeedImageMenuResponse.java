package com.foodymoody.be.feed.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedImageMenuResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private FeedImageResponse image;
    @JsonProperty
    private FeedMenuResponse menu;

    public FeedImageMenuResponse(String id, FeedImageResponse image, FeedMenuResponse menu) {
        this.id = id;
        this.image = image;
        this.menu = menu;
    }

}