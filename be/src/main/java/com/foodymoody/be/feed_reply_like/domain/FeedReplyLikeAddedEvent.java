package com.foodymoody.be.feed_reply_like.domain;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedReplyLikeAddedEvent implements NotificationEvent {

    private final FeedCommentId feedCommentId;
    private final FeedReplyId feedReplyId;
    private final MemberId fromMemberId;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private FeedReplyLikeAddedEvent(
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

    public static FeedReplyLikeAddedEvent of(
            FeedCommentId feedCommentId,
            FeedReplyId feedReplyId,
            MemberId fromMemberId,
            LocalDateTime createdAt
    ) {
        return new FeedReplyLikeAddedEvent(
                feedCommentId,
                feedReplyId,
                fromMemberId,
                NotificationType.FEED_COMMENT_REPLY_LIKED_ADDED_EVENT,
                createdAt
        );
    }

}
