package com.foodymoody.be.feed_like.domain.entity;

import com.foodymoody.be.common.event.EventManager;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedLike {

    @Id
    @Getter
    private FeedLikeId id;

    @AttributeOverride(name = "value", column = @Column(name = "feed_id"))
    @Getter
    private FeedId feedId;

    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    @Getter
    private MemberId memberId;

    @Getter
    private boolean isLiked;

    public FeedLike(
            FeedLikeId id,
            FeedId feedId,
            MemberId memberId,
            boolean isLiked
    ) {
        this.id = id;
        this.feedId = feedId;
        this.memberId = memberId;
        this.isLiked = isLiked;
        EventManager.raise(toEvent(feedId, memberId));
    }

    private static FeedLikeAddedEvent toEvent(FeedId feedId, MemberId memberId) {
        return FeedLikeAddedEvent.of(
                feedId,
                memberId,
                LocalDateTime.now()
        );
    }

}
