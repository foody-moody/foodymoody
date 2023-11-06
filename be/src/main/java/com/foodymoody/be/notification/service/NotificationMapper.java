package com.foodymoody.be.notification.service;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.notification.controller.dto.NotificationResponse;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public Notification toEntity(NotificationId notificationId, NotificationEvent event) {
        return new Notification(notificationId, event.getMemberId(), event.getMessage(),
                event.getNotificationType(), false);
    }

    public Slice<NotificationResponse> toDto(Slice<Notification> notifications) {
        return notifications.map(notification -> new NotificationResponse(notification.getId().getValue(),
                notification.getMessage(), notification.getType(),
                notification.isRead()));
    }
}
