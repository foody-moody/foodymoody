package com.foodymoody.be.notification.infra.event.util;

import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import java.time.LocalDateTime;

public class NotificationMapper {

    private NotificationMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Notification toNotification(NotificationId notificationId,
            String link, String message, String fromMemberId, String toMemberId, NotificationType notificationType,
            LocalDateTime createdAt) {
        return new Notification(notificationId, fromMemberId, toMemberId, link,
                message, notificationType, false, false, createdAt, createdAt);
    }

}
