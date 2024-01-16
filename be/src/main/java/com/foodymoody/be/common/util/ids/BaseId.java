package com.foodymoody.be.common.util.ids;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@MappedSuperclass
public abstract class BaseId implements Serializable {

    private static final long serialVersionUID = 536871008L;

    @Column(name = "id")
    @JsonProperty("id")
    @JsonValue
    protected String value;

    protected BaseId(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseId)) {
            return false;
        }
        BaseId baseId = (BaseId) o;
        return Objects.equals(getValue(), baseId.getValue());
    }
}
