package com.foodymoody.be.feed_heart_count.domain;

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
    private String feedId;
    private int count;

    @Version
    private Long version;

    public FeedHeartCount(String id, String feedId, int count) {
        this.id = id;
        this.feedId = feedId;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public String getFeedId() {
        return feedId;
    }

    public int getCount() {
        return count;
    }

    public Long getVersion() {
        return version;
    }

}
