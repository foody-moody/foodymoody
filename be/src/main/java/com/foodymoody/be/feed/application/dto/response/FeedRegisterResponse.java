package com.foodymoody.be.feed.application.dto.response;

public class FeedRegisterResponse {

    private final String id;

    public FeedRegisterResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
