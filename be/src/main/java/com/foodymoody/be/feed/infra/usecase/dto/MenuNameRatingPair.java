package com.foodymoody.be.feed.infra.usecase.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MenuNameRatingPair {

    private final String name;
    private final int rating;

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

}
