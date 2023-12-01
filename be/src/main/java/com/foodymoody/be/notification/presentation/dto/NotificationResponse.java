package com.foodymoody.be.notification.presentation.dto;

import com.foodymoody.be.common.event.NotificationType;

public class NotificationResponse {

    private String notificationId;
    private String message;
    private NotificationType type;
    private boolean isRead;

    public NotificationResponse(String notificationId, String message, NotificationType type, boolean isRead) {
        this.notificationId = notificationId;
        this.message = message;
        this.type = type;
        this.isRead = isRead;
    }

    public String getId() {
        return notificationId;
    }

    public String getMessage() {
        return message;
    }

    public NotificationType getType() {
        return type;
    }

    public boolean isRead() {
        return isRead;
    }
}
