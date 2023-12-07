package com.foodymoody.be.feed_heart.domain;

import com.foodymoody.be.common.util.ids.FeedHeartId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedHeart {

    @Id
    private FeedHeartId id;
    @AttributeOverride(name = "value", column = @Column(name = "feed_id"))
    private FeedId feedId;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;

    public FeedHeart(FeedHeartId id, FeedId feedId, MemberId memberId) {
        this.id = id;
        this.feedId = feedId;
        this.memberId = memberId;
    }

    public FeedHeartId getId() {
        return id;
    }

    public FeedId getFeedId() {
        return feedId;
    }

    public MemberId getMemberId() {
        return memberId;
    }

}
