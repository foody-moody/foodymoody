package com.foodymoody.be.comment.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCommentRequest {

    @Min(0)
    private long feedId;
    @NotBlank
    @Size(min = 1, max = 200)
    private String content;
}
