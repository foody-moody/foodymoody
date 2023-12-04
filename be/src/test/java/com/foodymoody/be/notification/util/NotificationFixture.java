package com.foodymoody.be.notification.util;

import com.foodymoody.be.comment.domain.entity.CommentAddedEvent;
import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

public class NotificationFixture {

    public static final LocalDateTime CREATE_AT = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
    public static final String NOTIFICATION_ID = "11231312";
    public static final String NOT_EXIST_NOTIFICATION_ID = "not exist notification id";
    public static final String MEMBER_ID = "1231312312";
    public static final String NOTIFICATION_MESSAGE = "새로운 댓글이 달렸습니다.";
    public static final String NOT_EXIST_MEMBER_ID = "not exist member id";
    public static final LocalDateTime UPDATE_AT = LocalDateTime.of(2021, 2, 3, 4, 5, 6);
    public static final String FEED_ID = "1";
    public static final CommentId COMMENT_ID = new CommentId("1");

    public static CommentAddedEvent commentAddNotificationEvent() {
        return CommentAddedEvent.of(FEED_ID, NOTIFICATION_MESSAGE, COMMENT_ID, MEMBER_ID,
                NotificationFixture.CREATE_AT);
    }

    public static NotificationId notificationId() {
        return NotificationIdFactory.from(NOTIFICATION_ID);
    }

    public static Notification notification() {
        return notification(notificationId());
    }

    public static Notification notification(NotificationId id) {
        return new Notification(id, "2", MEMBER_ID, "https://foodymoody.com/api/1", NOTIFICATION_MESSAGE,
                NotificationType.COMMENT_ADDED, false, false, CREATE_AT, UPDATE_AT);
    }

    public static Slice<Notification> notifications() {
        List<Notification> notifications = IntStream.range(0, 10)
                .mapToObj(i -> notification(NotificationIdFactory.from(i + "")))
                .collect(Collectors.toList());
        return new SliceImpl(notifications);
    }
}
