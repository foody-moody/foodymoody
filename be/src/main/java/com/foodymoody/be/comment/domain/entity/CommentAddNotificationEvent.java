package com.foodymoody.be.comment.domain.entity;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import java.time.LocalDateTime;

public class CommentAddNotificationEvent implements NotificationEvent {

    private final String feedId;
    private final String content;
    private final NotificationType notificationType;
    private final CommentId commentId;
    private final String memberId;
    private final LocalDateTime createdAt;

    private CommentAddNotificationEvent(
            String feedId,
            String content,
            NotificationType notificationType,
            CommentId commentId,
            String memberId,
            LocalDateTime createdAt
    ) {
        this.feedId = feedId;
        this.content = content;
        this.notificationType = notificationType;
        this.commentId = commentId;
        this.memberId = memberId;
        this.createdAt = createdAt;
    }

    public static CommentAddNotificationEvent of(
            String feedId,
            String content,
            CommentId commentId,
            String memberId,
            LocalDateTime createdAt
    ) {
        return new CommentAddNotificationEvent(
                feedId,
                content,
                NotificationType.COMMENT_ADDED,
                commentId,
                memberId,
                createdAt
        );
    }

    public String getFeedId() {
        return feedId;
    }

    public String getContent() {
        return content;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public CommentId getCommentId() {
        return commentId;
    }

    public String getMemberId() {
        return memberId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


}
