package com.foodymoody.be.feed.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FeedRegisterRequestMenu {

    private String name;
    private int numStar;

    public String getName() {
        return name;
    }

    public int getNumStar() {
        return numStar;
    }
}
