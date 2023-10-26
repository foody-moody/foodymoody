package com.foodymoody.be.comment.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCommentRequest {

    private String feedId;
    private String content;

}
