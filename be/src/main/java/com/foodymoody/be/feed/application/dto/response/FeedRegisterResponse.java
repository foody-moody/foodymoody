package com.foodymoody.be.feed.application.dto.response;

import com.foodymoody.be.common.util.ids.FeedId;

public class FeedRegisterResponse {

    private final FeedId id;

    public FeedRegisterResponse(FeedId id) {
        this.id = id;
    }

    public FeedId getId() {
        return id;
    }

}
