package com.foodymoody.be.feed.application.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FeedMenuResponse {

    private String name;
    private int rating;

    public static FeedMenuResponse from(String name, int rating) {
        return new FeedMenuResponse(name, rating);
    }

}
