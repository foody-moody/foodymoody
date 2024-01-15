package com.foodymoody.be.feed.application.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class FeedRegisterRequestMenu {

    private String name;
    private int rating;

    @Builder
    public FeedRegisterRequestMenu(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

}
