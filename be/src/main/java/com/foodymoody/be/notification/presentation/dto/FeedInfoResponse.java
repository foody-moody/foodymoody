package com.foodymoody.be.notification.presentation.dto;

import com.foodymoody.be.common.util.Content;
import lombok.Getter;

@Getter
public class FeedInfoResponse {

    private String feedId;
    private String imageUrl;
    private String commentId;
    private Content commentMessage;

    public FeedInfoResponse(String feedId, String imageUrl, String commentId, Content commentMessage) {
        this.feedId = feedId;
        this.imageUrl = imageUrl;
        this.commentId = commentId;
        this.commentMessage = commentMessage;
    }
}
