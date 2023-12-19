package com.foodymoody.be.feed.domain.entity;

import com.foodymoody.be.common.util.ids.StoreMoodId;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreMood {

    @EmbeddedId
    private StoreMoodId id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    public StoreMood(StoreMoodId id, String name) {
        this.id = id;
        this.name = name;
    }

    public StoreMood(StoreMoodId id, String name, Feed feed) {
        this.id = id;
        this.name = name;
        this.feed = feed;
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

    public Feed getFeed() {
        return feed;
    }

}
