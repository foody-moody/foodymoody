package com.foodymoody.be.member.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberId implements Serializable {

    private static final long serialVersionUID = -5351955236373496259L;

    private String id;

    public MemberId(String id) {
        this.id = id;
    }

    public static MemberId from(String generate) {
        return new MemberId(generate);
    }

    public String getId() {
        return id;
    }
}
