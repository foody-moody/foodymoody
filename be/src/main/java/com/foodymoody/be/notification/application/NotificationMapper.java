package com.foodymoody.be.notification.application;

import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import com.foodymoody.be.notification.presentation.dto.NotificationResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public static List<NotificationId> toNotificationID(List<String> notificationIds) {
        return notificationIds.stream()
                .map(NotificationIdFactory::from)
                .collect(Collectors.toList());
    }

    public Slice<NotificationResponse> generateResponseDtoSliceFromNotifications(Slice<Notification> notifications) {
        return notifications.map(this::generateResponseDtoFromNotification);
    }

    public NotificationResponse generateResponseDtoFromNotification(Notification notification) {
        return new NotificationResponse(notification.getId().getValue(), notification.getMessage(),
                notification.getType(), notification.isRead());
    }
}
