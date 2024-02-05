package com.foodymoody.be.notification.util;

import static com.foodymoody.be.feed_comment.util.CommentFixture.content;

import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionCommentNotificationDetails;
import java.time.LocalDateTime;
import org.jetbrains.annotations.NotNull;

public class NotificationFixture {

    public static final LocalDateTime CREATE_AT = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
    public static final String NOTIFICATION_ID = "11231312";
    public static final String TO_MEMBER_ID = "1231312312";
    public static final String NOT_EXIST_MEMBER_ID = "not exist member id";
    public static final LocalDateTime UPDATE_AT = LocalDateTime.of(2021, 2, 3, 4, 5, 6);
    public static final String FEED_ID = "1";
    public static final String FROM_MEMBER_ID = "2";

    public static NotificationId notificationId() {
        return IdFactory.createNotificationId(NOTIFICATION_ID);
    }

    public static Notification notification() {
        return notification(notificationId());
    }

    public static Notification notification(NotificationId id) {
        return new Notification(id, getFromMemberId(), getToMemberId(), notificationDetails(),
                                NotificationType.FEED_ADDED_EVENT,
                                false, false,
                                CREATE_AT, UPDATE_AT
        );
    }

    public static NotificationDetails notificationDetails() {
        return new FeedCollectionCommentNotificationDetails(
                IdFactory.createFeedCollectionId(),
                "title",
                "thumbnailUrl",
                IdFactory.createFeedCollectionCommentId(),
                content()
        );
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
}
