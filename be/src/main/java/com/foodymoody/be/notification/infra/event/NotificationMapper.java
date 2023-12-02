package com.foodymoody.be.notification.infra.event;

import com.foodymoody.be.comment.domain.entity.CommentAddedEvent;
import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;

public class NotificationMapper {

    private NotificationMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Notification toNotification(CommentAddedEvent event, NotificationId notificationId,
            Member member, Feed feed, String link, String message) {
        return new Notification(notificationId, member.getMemberId(), feed.getMemberId(), link,
                message, event.getNotificationType(), false, false, event.getCreatedAt(), event.getCreatedAt());
    }

}
