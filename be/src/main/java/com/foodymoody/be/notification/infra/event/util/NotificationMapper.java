package com.foodymoody.be.notification.infra.event.util;

import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.domain.FeedNotificationId;
import java.time.LocalDateTime;

public class NotificationMapper {

    private NotificationMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static FeedNotification toNotification(FeedNotificationId feedNotificationId,
            String message, String fromMemberId, String toMemberId, String feedId, CommentId commentId,
            NotificationType notificationType, LocalDateTime createdAt) {
        return new FeedNotification(feedNotificationId, fromMemberId, toMemberId, message, feedId, commentId,
                notificationType, false, false, createdAt, createdAt);
    }

}
