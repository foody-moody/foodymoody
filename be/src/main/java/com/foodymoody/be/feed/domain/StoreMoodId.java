package com.foodymoody.be.feed.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreMoodId implements Serializable {

    private static final long serialVersionUID = -5351955236373496259L;

    private String id;

    public StoreMoodId(String id) {
        this.id = id;
    }

    public static StoreMoodId from(String generate) {
        return new StoreMoodId(generate);
    }

    public String getId() {
        return id;
    }
}
