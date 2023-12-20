package com.foodymoody.be.feed.domain.entity;

import com.foodymoody.be.common.util.ids.StoreMoodId;
import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreMood {

    @EmbeddedId
    private StoreMoodId id;
    private String name;

    public StoreMood(StoreMoodId id, String name) {
        this.id = id;
        this.name = name;
    }

    public StoreMoodId getStoreMoodId() {
        return id;
    }

    public String getId() {
        return id.getValue();
    }

    public String getName() {
        return name;
    }

}
