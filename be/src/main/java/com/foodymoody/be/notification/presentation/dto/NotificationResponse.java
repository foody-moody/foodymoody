package com.foodymoody.be.notification.presentation.dto;

import com.foodymoody.be.common.event.NotificationType;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class NotificationResponse {

    private String notificationId;
    private Sender sender;
    private FeedInfoResponse target;
    private NotificationType type;
    private boolean isRead;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public NotificationResponse(String notificationId, Sender sender, FeedInfoResponse target, NotificationType type,
            boolean isRead, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.notificationId = notificationId;
        this.sender = sender;
        this.target = target;
        this.type = type;
        this.isRead = isRead;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
