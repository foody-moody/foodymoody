package com.foodymoody.be.notification.presentation.dto;

import lombok.Getter;

@Getter
public class FeedInfoResponse {

    private String feedId;
    private String imageUrl;
    private String commentId;
    private String commentMessage;

    public FeedInfoResponse(String feedId, String imageUrl, String commentId, String commentMessage) {
        this.feedId = feedId;
        this.imageUrl = imageUrl;
        this.commentId = commentId;
        this.commentMessage = commentMessage;
    }
}
