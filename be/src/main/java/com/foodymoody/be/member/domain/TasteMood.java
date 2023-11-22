package com.foodymoody.be.member.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TasteMood {

    @EmbeddedId
    private TasteMoodId id;
    private String name;

    public TasteMood(TasteMoodId id, String name) {
        this.id = id;
        this.name = name;
    }

    public TasteMoodId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
