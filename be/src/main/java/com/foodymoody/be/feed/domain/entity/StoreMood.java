package com.foodymoody.be.feed.domain.entity;

import com.foodymoody.be.common.util.ids.StoreMoodId;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreMood {

    @Id
    private StoreMoodId id;
    private String name;

    public StoreMood(StoreMoodId id, String name) {
        this.id = id;
        this.name = name;
    }

    public StoreMoodId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
