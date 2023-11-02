package com.foodymoody.be.notification.service;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public Notification toEntity(NotificationId notificationId, NotificationEvent event) {
        return new Notification(notificationId, event.getMemberId(), event.getMessage(),
                event.getNotificationType(), false);
    }
}
