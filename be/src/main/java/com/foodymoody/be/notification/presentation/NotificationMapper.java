package com.foodymoody.be.notification.presentation;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.NotificationId;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationId toNotificationID(String notificationId) {
        return IdFactory.createNotificationId(notificationId);
    }
}
