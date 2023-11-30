package com.foodymoody.be.comment.application.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterReplyRequest {

    @NotNull
    @NotBlank
    private String content;

}
