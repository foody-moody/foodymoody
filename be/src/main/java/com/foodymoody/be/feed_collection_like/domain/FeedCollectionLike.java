package com.foodymoody.be.feed_collection_like.domain;

import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "feed_collection_like",
        indexes = {
                @Index(name = "idx_feed_collection_like_on_feed_collection_id", columnList = "member_id, feed_collection_id")
        }
)
public class FeedCollectionLike {

    @Getter
    @Id
    private FeedCollectionLikeId id;
    @AttributeOverride(name = "value", column = @Column(name = "feed_collection_id"))
    private FeedCollectionId feedCollectionId;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    private LocalDateTime createdAt;

    public FeedCollectionLike(
            FeedCollectionLikeId id, FeedCollectionId feedCollectionId, MemberId memberId, LocalDateTime createdAt
    ) {
        this.id = id;
        this.feedCollectionId = feedCollectionId;
        this.memberId = memberId;
        this.createdAt = createdAt;
        Events.raise(toEvent(feedCollectionId, memberId, createdAt));
    }

    private static FeedCollectionLikeAddedEvent toEvent(
            FeedCollectionId feedCollectionId,
            MemberId memberId,
            LocalDateTime createdAt
    ) {
        return FeedCollectionLikeAddedEvent.of(memberId, feedCollectionId, createdAt);
    }
}
