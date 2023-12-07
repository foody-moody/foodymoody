package com.foodymoody.be.notification.application;

import com.foodymoody.be.common.util.ids.FeedNotificationId;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public List<FeedNotificationId> toNotificationID(List<String> notificationIds) {
        return notificationIds.stream()
                .map(NotificationIdFactory::from)
                .collect(Collectors.toList());
    }
}
