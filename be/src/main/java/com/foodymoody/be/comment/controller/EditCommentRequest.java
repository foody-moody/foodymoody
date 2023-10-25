package com.foodymoody.be.comment.controller;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditCommentRequest {

    @NotBlank
    String content;
}
