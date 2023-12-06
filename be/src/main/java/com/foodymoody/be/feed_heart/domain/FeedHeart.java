package com.foodymoody.be.feed_heart.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedHeart {

    @Id
    private String id;
    private String feedId;
    private String memberId;

    public FeedHeart(String id, String feedId, String memberId) {
        this.id = id;
        this.feedId = feedId;
        this.memberId = memberId;
    }

    public String getId() {
        return id;
    }

    public String getFeedId() {
        return feedId;
    }

    public String getMemberId() {
        return memberId;
    }

}
