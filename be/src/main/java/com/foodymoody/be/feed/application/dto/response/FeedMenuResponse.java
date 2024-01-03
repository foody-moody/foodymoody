package com.foodymoody.be.feed.application.dto.response;

public class FeedMenuResponse {

    private String name;
    private int rating;

    public FeedMenuResponse(String name, int rating) {
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
