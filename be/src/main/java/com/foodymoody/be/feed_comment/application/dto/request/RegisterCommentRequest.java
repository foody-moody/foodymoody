package com.foodymoody.be.feed_comment.application.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCommentRequest {

    @NotNull
    @NotBlank
    private String feedId;
    @NotNull
    @NotBlank
    private String content;

}
