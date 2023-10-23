package com.foodymoody.be.comment.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCommentRequest {

    private long feedId;
    private String content;

    public RegisterCommentRequest() {
    }

    public RegisterCommentRequest(long feedId, String content) {
        this.feedId = feedId;
        this.content = content;
    }
}
