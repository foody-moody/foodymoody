package com.foodymoody.be.feed.domain.repository.dto;

import lombok.Getter;

@Getter
public class MemberProfileFeedPreviewResponse {

    private String id;
    private String imageUrl;

    public MemberProfileFeedPreviewResponse(String id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }
}
