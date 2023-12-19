package com.foodymoody.be.common.util;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class CommentContent {

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String content;
}
