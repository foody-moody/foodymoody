package com.foodymoody.be.common.util.ids;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseId implements Serializable {

    private static final long serialVersionUID = 536871008L;

    @Column(name = "id")
    @JsonProperty("id")
    @JsonValue
    @EqualsAndHashCode.Include
    protected String value;

    protected BaseId(String value) {
        this.value = value;
    }

}
