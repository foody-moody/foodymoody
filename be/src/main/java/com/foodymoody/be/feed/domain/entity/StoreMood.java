package com.foodymoody.be.feed.domain.entity;

import com.foodymoody.be.common.util.ids.StoreMoodId;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreMood {

    @Id
    @Getter
    private StoreMoodId id;

    @Getter
    private String name;

    public StoreMood(StoreMoodId id, String name) {
        this.id = id;
        this.name = name;
    }

}
