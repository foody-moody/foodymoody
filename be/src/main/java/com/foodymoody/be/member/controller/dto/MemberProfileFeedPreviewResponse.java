package com.foodymoody.be.member.controller.dto;

import lombok.Getter;

@Getter
public class MemberProfileFeedPreviewResponse {

    private String id;
    private String imageUrl;

    private MemberProfileFeedPreviewResponse(String id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }
}