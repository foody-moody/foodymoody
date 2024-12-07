package com.foodymoody.be.feed_collection_reply_like.domain;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedCollectionReplyLikeAddedEvent implements NotificationEvent {

    private final FeedCollectionReplyLikeId id;
    private final MemberId fromMemberId;
    private final FeedCollectionReplyId feedCollectionReplyId;
    private final FeedCollectionCommentId feedCollectionCommentId;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private FeedCollectionReplyLikeAddedEvent(
            FeedCollectionReplyLikeId id,
            MemberId memberId,
            FeedCollectionReplyId feedCollectionReplyId,
            FeedCollectionCommentId feedCollectionCommentId,
            NotificationType notificationType,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.fromMemberId = memberId;
        this.feedCollectionReplyId = feedCollectionReplyId;
        this.feedCollectionCommentId = feedCollectionCommentId;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static FeedCollectionReplyLikeAddedEvent of(
            FeedCollectionReplyLikeId id,
            MemberId memberId,
            FeedCollectionReplyId feedCollectionReplyId,
            FeedCollectionCommentId feedCollectionCommentId,
            LocalDateTime createdAt
    ) {
        return new FeedCollectionReplyLikeAddedEvent(
                id,
                memberId,
                feedCollectionReplyId,
                feedCollectionCommentId,
                NotificationType.FEED_COLLECTION_COMMENT_REPLY_LIKED_ADDED_EVENT,
                createdAt
        );
    }

}
