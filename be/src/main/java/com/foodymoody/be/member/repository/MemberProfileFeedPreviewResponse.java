package com.foodymoody.be.member.repository;

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