package com.foodymoody.be.feed_like.domain.entity;

import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedLike {

    @Id
    private FeedLikeId id;
    @AttributeOverride(name = "value", column = @Column(name = "feed_id"))
    private FeedId feedId;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    private boolean isLiked;

    public FeedLike(FeedLikeId id, FeedId feedId, MemberId memberId, boolean isLiked) {
        this.id = id;
        this.feedId = feedId;
        this.memberId = memberId;
        this.isLiked = isLiked;
        Events.raise(toEvent(feedId, memberId));
    }

    public FeedLikeId getId() {
        return id;
    }

    public FeedId getFeedId() {
        return feedId;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public boolean isLiked() {
        return isLiked;
    }

    private static FeedLikeAddedEvent toEvent(FeedId feedId, MemberId memberId) {
        return FeedLikeAddedEvent.of(
                feedId,
                memberId,
                LocalDateTime.now()
        );
    }

}
