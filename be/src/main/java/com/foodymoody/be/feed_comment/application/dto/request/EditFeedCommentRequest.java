package com.foodymoody.be.feed_comment.application.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditFeedCommentRequest {

    @Size(max = 200)
    @NotBlank
    String content;
}