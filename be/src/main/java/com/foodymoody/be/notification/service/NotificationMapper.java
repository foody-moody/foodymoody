package com.foodymoody.be.notification.service;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.notification.controller.dto.NotificationResponse;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public static List<NotificationId> toNotificationID(List<String> notificationIds) {
        return notificationIds.stream()
                .map(NotificationId::from)
                .collect(Collectors.toList());
    }

    public Notification createNotificationEntityFromEvent(NotificationId notificationId, NotificationEvent event) {
        return new Notification(notificationId, event.getMemberId(), event.getMessage(),
                event.getNotificationType(), false, false, event.getCreatedAt(), event.getCreatedAt());
    }

    public Slice<NotificationResponse> generateResponseDtoSliceFromNotifications(Slice<Notification> notifications) {
        return notifications.map(this::generateResponseDtoFromNotification);
    }

    public NotificationResponse generateResponseDtoFromNotification(Notification notification) {
        return new NotificationResponse(notification.getId().getValue(), notification.getMessage(),
                notification.getType(), notification.isRead());
    }
}
