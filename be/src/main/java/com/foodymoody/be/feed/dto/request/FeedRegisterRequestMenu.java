package com.foodymoody.be.feed.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedRegisterRequestMenu {

    private String name;
    private int numStar;

    @Builder
    public FeedRegisterRequestMenu(String name, int numStar) {
        this.name = name;
        this.numStar = numStar;
    }

    public String getName() {
        return name;
    }

    public int getNumStar() {
        return numStar;
    }

}
