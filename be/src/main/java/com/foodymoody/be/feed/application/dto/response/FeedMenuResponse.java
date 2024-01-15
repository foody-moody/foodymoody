package com.foodymoody.be.feed.application.dto.response;

import lombok.Getter;

@Getter
public class FeedMenuResponse {

    private String name;
    private int rating;

    public FeedMenuResponse(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

}
