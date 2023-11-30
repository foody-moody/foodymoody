package com.foodymoody.be.common.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@MappedSuperclass
public abstract class BaseId implements Serializable {

    private static final long serialVersionUID = 536871008L;
    @JsonProperty("id")
    protected String value;

    protected BaseId(String value) {
        this.value = value;
    }
}
