package com.foodymoody.be.notification.util;

import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedNotificationId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import java.time.LocalDateTime;
import org.jetbrains.annotations.NotNull;

public class NotificationFixture {

    public static final LocalDateTime CREATE_AT = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
    public static final String NOTIFICATION_ID = "11231312";
    public static final String TO_MEMBER_ID = "1231312312";
    public static final String NOTIFICATION_MESSAGE = "새로운 댓글이 달렸습니다.";
    public static final String NOT_EXIST_MEMBER_ID = "not exist member id";
    public static final LocalDateTime UPDATE_AT = LocalDateTime.of(2021, 2, 3, 4, 5, 6);
    public static final String FEED_ID = "1";
    public static final CommentId COMMENT_ID = new CommentId("1");
    public static final String FROM_MEMBER_ID = "2";

    public static FeedNotificationId notificationId() {
        return NotificationIdFactory.from(NOTIFICATION_ID);
    }

    public static FeedNotification notification() {
        return notification(notificationId());
    }

    public static FeedNotification notification(FeedNotificationId id) {
        return new FeedNotification(id, getFromMemberId(), getToMemberId(), NOTIFICATION_MESSAGE, getFeedId(),
                COMMENT_ID, NotificationType.COMMENT_ADDED_EVENT, false, false, CREATE_AT, UPDATE_AT);
    }

    @NotNull
    public static MemberId getToMemberId() {
        return IdFactory.createMemberId(TO_MEMBER_ID);
    }

    @NotNull
    public static MemberId getFromMemberId() {
        return IdFactory.createMemberId(FROM_MEMBER_ID);
    }

    @NotNull
    public static MemberId getNotExistMemberId() {
        return IdFactory.createMemberId(NOT_EXIST_MEMBER_ID);
    }

    private static FeedId getFeedId() {
        return IdFactory.createFeedId(FEED_ID);
    }
}
