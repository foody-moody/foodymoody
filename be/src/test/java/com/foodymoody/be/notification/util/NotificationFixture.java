package com.foodymoody.be.notification.util;

import com.foodymoody.be.comment.domain.CommentAddNotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

public class NotificationFixture {

    public static final LocalDateTime CREATE_AT = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
    public static final String NOTIFICATION_ID = "11231312";

    public static CommentAddNotificationEvent commentAddNotificationEvent() {
        return CommentAddNotificationEvent.of("memberId", "message",
                NotificationType.COMMENT_ADDED, NotificationFixture.CREATE_AT);
    }

    public static NotificationId notificationId() {
        return NotificationId.from(NOTIFICATION_ID);
    }

    public static Notification notification() {
        return notification(notificationId());
    }

    public static Notification notification(NotificationId id) {
        return new Notification(id, "memberId", "message",
                NotificationType.COMMENT_ADDED, false, false);
    }

    public static Slice<Notification> notifications() {
        List<Notification> notifications = IntStream.range(0, 10)
                .mapToObj(i -> notification(NotificationId.from(i + "")))
                .collect(Collectors.toList());
        return new SliceImpl(notifications);
    }
}
