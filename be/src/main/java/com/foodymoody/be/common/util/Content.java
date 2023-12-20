package com.foodymoody.be.common.util;

import com.fasterxml.jackson.annotation.JsonValue;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Content {

    @JsonValue
    @NotNull
    @NotBlank
    @Size(max = 200)
    private String value;
}
