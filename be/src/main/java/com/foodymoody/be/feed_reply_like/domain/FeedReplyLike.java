package com.foodymoody.be.feed_reply_like.domain;

import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.FeedReplyLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedReplyLike {

    @EmbeddedId
    private FeedReplyLikeId id;
    @AttributeOverride(name = "value", column = @Column(name = "feed_comment_id"))
    private FeedCommentId feedCommentId;
    @AttributeOverride(name = "value", column = @Column(name = "reply_id"))
    private FeedReplyId feedReplyId;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedReplyLike(
            FeedReplyLikeId id,
            FeedCommentId feedCommentId,
            FeedReplyId feedReplyId,
            MemberId memberId,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.feedCommentId = feedCommentId;
        this.feedReplyId = feedReplyId;
        this.memberId = memberId;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        Events.raise(toEvent(feedCommentId, feedReplyId, memberId, createdAt));
    }

    private static FeedReplyLikedAddedEvent toEvent(
            FeedCommentId feedCommentId,
            FeedReplyId feedReplyId,
            MemberId memberId,
            LocalDateTime createdAt
    ) {
        return FeedReplyLikedAddedEvent.of(
                feedCommentId,
                feedReplyId,
                memberId,
                createdAt
        );
    }
}
