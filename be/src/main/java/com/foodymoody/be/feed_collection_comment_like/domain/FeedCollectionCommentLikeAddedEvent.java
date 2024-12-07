package com.foodymoody.be.feed_collection_comment_like.domain;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedCollectionCommentLikeAddedEvent implements NotificationEvent {

    private final FeedCollectionCommentId feedCollectionCommentId;
    private final MemberId fromMemberId;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private FeedCollectionCommentLikeAddedEvent(
            FeedCollectionCommentId feedCollectionCommentId,
            MemberId fromMemberId,
            NotificationType notificationType,
            LocalDateTime createdAt
    ) {
        this.feedCollectionCommentId = feedCollectionCommentId;
        this.fromMemberId = fromMemberId;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static FeedCollectionCommentLikeAddedEvent of(
            FeedCollectionCommentId feedCollectionCommentId,
            MemberId fromMemberId,
            LocalDateTime createdAt
    ) {
        return new FeedCollectionCommentLikeAddedEvent(
                feedCollectionCommentId,
                fromMemberId,
                NotificationType.FEED_COLLECTION_COMMENT_LIKED_ADDED_EVENT,
                createdAt
        );
    }

}
