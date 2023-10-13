package com.foodymoody.be.feed.dto;

import lombok.Getter;

@Getter
public class FeedRegisterResponse {

    private Long id;

    public FeedRegisterResponse(Long id) {
        this.id = id;
    }
}
