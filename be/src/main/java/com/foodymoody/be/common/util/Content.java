package com.foodymoody.be.common.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Content {

    @JsonValue
    @NotNull
    @NotBlank
    @Size(max = 200)
    @JsonProperty("content")
    private String value;

    public Content(String value) {
        this.value = value;
    }
}
