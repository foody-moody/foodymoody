package com.foodymoody.be.feed_collection_comment_like.domain;

import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedCollectionCommentLike {

    @Getter
    @Id
    private FeedCollectionCommentLikeId id;
    @AttributeOverride(name = "value", column = @javax.persistence.Column(name = "comment_id"))
    private FeedCollectionCommentId commentId;
    @AttributeOverride(name = "value", column = @javax.persistence.Column(name = "member_id"))
    private MemberId memberId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedCollectionCommentLike(
            FeedCollectionCommentLikeId id,
            FeedCollectionCommentId commentId,
            MemberId memberId,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.commentId = commentId;
        this.memberId = memberId;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        Events.raise(toEvent(commentId, memberId, createdAt));
    }

    private static FeedCollectionCommentLikeAddedEvent toEvent(
            FeedCollectionCommentId commentId,
            MemberId memberId,
            LocalDateTime createdAt
    ) {
        return FeedCollectionCommentLikeAddedEvent.of(commentId, memberId, createdAt);
    }
}
