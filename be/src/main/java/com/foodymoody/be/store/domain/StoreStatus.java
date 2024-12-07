package com.foodymoody.be.store.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class StoreStatus {

    @Id
    private int code;

    @Enumerated(EnumType.STRING)
    private StatusType type;

    public boolean isClosed() {
        return type.isClosed();
    }

    public enum StatusType {
        OPEN("영업/정상"),
        TEMPORARILY_CLOSED("휴업"),
        CLOSED("폐업") {
            @Override
            public boolean isClosed() {
                return true;
            }
        },
        ETC("취소/말소/만료/정지/중지");

        private final String description;

        public boolean isClosed() {
            return false;
        }

        StatusType(String description) {
            this.description = description;
        }
    }

}
