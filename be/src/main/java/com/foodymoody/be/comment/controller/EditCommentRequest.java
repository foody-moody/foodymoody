package com.foodymoody.be.comment.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditCommentRequest {

    @Size(max = 200)
    @NotBlank
    String content;
}
