package com.foodymoody.be.comment.domain;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import java.time.LocalDateTime;

public class CommentAddNotificationEvent implements NotificationEvent {

    private String memberId;
    private String message;
    private NotificationType notificationType;
    private LocalDateTime createdAt;

    private CommentAddNotificationEvent(String memberId, String message, NotificationType notificationType,
            LocalDateTime createdAt) {
        this.memberId = memberId;
        this.message = message;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static CommentAddNotificationEvent of(String memberId, String message, NotificationType notificationType,
            LocalDateTime createdAt) {
        return new CommentAddNotificationEvent(memberId, message, notificationType, createdAt);
    }

    @Override
    public NotificationType getNotificationType() {
        return notificationType;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getMemberId() {
        return memberId;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
