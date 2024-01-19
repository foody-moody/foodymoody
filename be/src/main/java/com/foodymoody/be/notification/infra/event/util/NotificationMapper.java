package com.foodymoody.be.notification.infra.event.util;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.notification.domain.Notification;
import java.util.Map;

public class NotificationMapper {

    private NotificationMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Notification toNotification(
            NotificationEvent event,
            NotificationId notificationId,
            Map<String, Object> details,
            MemberId toMemberId
    ) {
        return new Notification(
                notificationId,
                event.getFromMemberId(),
                toMemberId,
                details,
                event.getNotificationType(),
                false,
                false,
                event.getCreatedAt(),
                event.getCreatedAt()
        );
    }
}
