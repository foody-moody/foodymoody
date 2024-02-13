package com.foodymoody.be.feed.application.usecase.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MenuNameRatingPair {

    private String name;
    private int rating;

    /**
     * FeedJpaRepository에서 사용 중
     */
    public MenuNameRatingPair(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

}
