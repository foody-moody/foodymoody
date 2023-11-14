package com.foodymoody.be.common;

import io.jsonwebtoken.lang.Assert;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class WrappedId implements Serializable {

    protected String id;

    public WrappedId() {
    }

    public WrappedId(String id) {
        Assert.notNull(id);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean equals(String id) {
        if (Objects.isNull(id)) {
            return false;
        }
        return this.id.equals(id);
    }

    public void assertEquals(String id) {
        if (!equals(id)) {
//            TODO 적절한 예외 메세지 추가
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WrappedId)) {
            return false;
        }
        WrappedId wrappedId = (WrappedId) o;
        return Objects.equals(getId(), wrappedId.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
