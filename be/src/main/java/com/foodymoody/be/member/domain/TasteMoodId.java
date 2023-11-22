package com.foodymoody.be.member.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TasteMoodId implements Serializable {

    private static final long serialVersionUID = -5351955236373496259L;

    private String id;

    public TasteMoodId(String id) {
        this.id = id;
    }

    public static TasteMoodId from(String generate) {
        return new TasteMoodId(generate);
    }

    public String getId() {
        return id;
    }
}
