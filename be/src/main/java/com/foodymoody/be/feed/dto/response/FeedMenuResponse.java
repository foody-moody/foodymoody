package com.foodymoody.be.feed.dto.response;

public class FeedMenuResponse {

    private String name;
    private int numStar;

    public FeedMenuResponse(String name, int numStar) {
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
