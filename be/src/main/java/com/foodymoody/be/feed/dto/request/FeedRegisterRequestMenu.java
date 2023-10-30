package com.foodymoody.be.feed.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedRegisterRequestMenu {

    private String name;
    private int rating;

    @Builder
    public FeedRegisterRequestMenu(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

}
