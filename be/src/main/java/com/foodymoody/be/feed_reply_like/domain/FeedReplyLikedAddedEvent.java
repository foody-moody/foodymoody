package com.foodymoody.be.feed_reply_like.domain;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedReplyLikedAddedEvent implements NotificationEvent {

    private final FeedCommentId feedCommentId;
    private final FeedReplyId feedReplyId;
    private final MemberId fromMemberId;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private FeedReplyLikedAddedEvent(
            FeedCommentId feedCommentId,
            FeedReplyId feedReplyId,
            MemberId fromMemberId,
            NotificationType notificationType,
            LocalDateTime createdAt
    ) {
        this.feedCommentId = feedCommentId;
        this.feedReplyId = feedReplyId;
        this.fromMemberId = fromMemberId;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static FeedReplyLikedAddedEvent of(
            FeedCommentId feedCommentId,
            FeedReplyId feedReplyId,
            MemberId fromMemberId,
            LocalDateTime createdAt
    ) {
        return new FeedReplyLikedAddedEvent(
                feedCommentId,
                feedReplyId,
                fromMemberId,
                NotificationType.FEED_COMMENT_REPLY_LIKED_ADDED_EVENT,
                createdAt
        );
    }
}
