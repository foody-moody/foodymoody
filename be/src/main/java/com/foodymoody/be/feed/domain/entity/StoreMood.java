package com.foodymoody.be.feed.domain.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreMood {

    @EmbeddedId
    private StoreMoodId id;
    private String name;

    public String getId() {
        return id.getId();
    }

    public String getName() {
        return name;
    }
}
