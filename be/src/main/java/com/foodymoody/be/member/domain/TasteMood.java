package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.util.ids.TasteMoodId;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TasteMood {

    @EmbeddedId
    @EqualsAndHashCode.Include
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
