package com.foodymoody.be.feed_heart_count.domain;

import com.foodymoody.be.common.util.ids.FeedId;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedHeartCount {

    @Id
    private String id;
    @AttributeOverride(name = "value", column = @Column(name = "feed_id"))
    private FeedId feedId;
    private int count;

    @Version
    private Long version;

    public FeedHeartCount(String id, FeedId feedId, int count) {
        this.id = id;
        this.feedId = feedId;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public FeedId getFeedId() {
        return feedId;
    }

    public int getCount() {
        return count;
    }

    public Long getVersion() {
        return version;
    }

}
