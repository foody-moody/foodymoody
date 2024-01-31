package com.foodymoody.be.feed_comment_like.domain;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedCommentLikeAddedEvent implements NotificationEvent {

    private final FeedCommentId feedCommentId;
    private final MemberId fromMemberId;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private FeedCommentLikeAddedEvent(
            FeedCommentId feedCommentId,
            MemberId fromMemberId,
            NotificationType notificationType,
            LocalDateTime createdAt
    ) {
        this.feedCommentId = feedCommentId;
        this.fromMemberId = fromMemberId;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static FeedCommentLikeAddedEvent of(
            FeedCommentId feedCommentId,
            MemberId fromMemberId,
            LocalDateTime createdAt
    ) {
        return new FeedCommentLikeAddedEvent(
                feedCommentId,
                fromMemberId,
                NotificationType.FEED_COMMENT_LIKED_ADDED_EVENT,
                createdAt
        );
    }
}
