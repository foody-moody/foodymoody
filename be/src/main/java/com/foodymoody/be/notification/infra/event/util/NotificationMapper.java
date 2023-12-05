package com.foodymoody.be.notification.infra.event.util;

import com.foodymoody.be.comment.domain.entity.CommentAddedEvent;
import com.foodymoody.be.comment.domain.entity.CommentRepliedAddedEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.domain.FeedNotificationId;

public class NotificationMapper {

    private NotificationMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static FeedNotification toNotification(CommentRepliedAddedEvent event,
            FeedNotificationId feedNotificationId) {
        return new FeedNotification(feedNotificationId, event.getContent(),
                event.getFromMemberId(), event.getToMemberId(), event.getFeedId(), event.getCommentId(),
                NotificationType.REPLY_ADDED_EVENT, false, false, event.getCreatedAt(), event.getCreatedAt());
    }

    public static FeedNotification toNotification(CommentAddedEvent event, FeedNotificationId feedNotificationId,
            Feed feed) {
        return new FeedNotification(feedNotificationId, event.getContent(), event.getMemberId(),
                feed.getMemberId(), event.getFeedId(), event.getCommentId(), event.getNotificationType(),
                false, false, event.getCreatedAt(), event.getCreatedAt());
    }

}
