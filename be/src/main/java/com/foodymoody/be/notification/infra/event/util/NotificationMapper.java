package com.foodymoody.be.notification.infra.event.util;

import com.foodymoody.be.comment.domain.entity.CommentAddedEvent;
import com.foodymoody.be.comment.domain.entity.CommentRepliedAddedEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.FeedNotificationId;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.notification.domain.FeedNotification;

public class NotificationMapper {

    private NotificationMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static FeedNotification toNotification(
            CommentRepliedAddedEvent event,
            FeedNotificationId feedNotificationId
    ) {
        return new FeedNotification(feedNotificationId, event.getFromMemberId(), event.getToMemberId(),
                                    event.getContent(), event.getFeedId(), event.getCommentId(),
                                    NotificationType.FEED_COMMENT_ADDED_EVENT, false,
                                    false, event.getCreatedAt(), event.getCreatedAt()
        );
    }

    public static FeedNotification toNotification(
            CommentAddedEvent event, FeedNotificationId feedNotificationId,
            Feed feed
    ) {
        return new FeedNotification(feedNotificationId, event.getMemberId(), feed.getMemberId(), event.getContent(),
                                    event.getFeedId(), event.getCommentId(), event.getNotificationType(),
                                    false, false, event.getCreatedAt(), event.getCreatedAt()
        );
    }

}
