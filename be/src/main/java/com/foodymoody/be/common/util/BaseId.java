package com.foodymoody.be.common.util;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseId implements Serializable {

    private static final long serialVersionUID = 536871008L;

    protected String value;

    protected BaseId() {
    }

    protected BaseId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
