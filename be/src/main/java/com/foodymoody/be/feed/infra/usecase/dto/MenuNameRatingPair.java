package com.foodymoody.be.feed.infra.usecase.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MenuNameRatingPair {

    private String name;
    private int rating;

    public MenuNameRatingPair(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

}
