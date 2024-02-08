package com.foodymoody.be.feed_comment.persentation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterFeedReplyRequest {

    @NotNull
    @NotBlank
    private String content;

}
