package com.foodymoody.be.notification.application.usecase.dto;

import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.notification.domain.NotificationDetails;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * The NotificationResponse class represents a response containing information about a notification.
 */
@Getter
public class NotificationResponse {

    /**
     * The ID of the notification.
     */
    private final NotificationId notificationId;
    /**
     * The sender of the notification.
     */
    private final SenderResponse sender;
    /**
     * The target of the notification.
     */
    private final NotificationDetails target;
    /**
     * The type of the notification.
     */
    private final NotificationType type;
    /**
     * Whether the notification has been read.
     */
    private final boolean isRead;
    /**
     * The creation date of the notification.
     */
    private final LocalDateTime createdAt;
    /**
     * The update date of the notification.
     */
    private final LocalDateTime updatedAt;

    private NotificationResponse(
            NotificationId notificationId,
            SenderResponse sender,
            NotificationDetails target,
            NotificationType type,
            boolean isRead,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.notificationId = notificationId;
        this.sender = sender;
        this.target = target;
        this.type = type;
        this.isRead = isRead;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Creates a new NotificationResponse object.
     *
     * @param notificationId The ID of the notification.
     * @param sender         The sender of the notification.
     * @param target         The target of the notification.
     * @param type           The type of the notification.
     * @param isRead         Whether the notification has been read.
     * @param createdAt      The creation date of the notification.
     * @param updatedAt      The update date of the notification.
     * @return The created NotificationResponse object.
     */
    public static NotificationResponse of(
            NotificationId notificationId,
            SenderResponse sender,
            NotificationDetails target,
            NotificationType type,
            boolean isRead,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        return new NotificationResponse(
                notificationId,
                sender,
                target,
                type,
                isRead,
                createdAt,
                updatedAt
        );
    }

}
