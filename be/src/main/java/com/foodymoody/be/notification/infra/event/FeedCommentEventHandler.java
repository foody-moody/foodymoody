package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentAddedEvent;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCommentNotificationDetails;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * The CommentEventHandler class is responsible for handling comment added events and saving notifications for those
 * events.
 */
@RequiredArgsConstructor
@Service
public class FeedCommentEventHandler {

    private final NotificationWriteService notificationService;
    private final FeedReadService feedReadService;
    private final NotificationSettingReadService notificationSettingService;

    /**
     * Saves a notification for a comment added event.
     *
     * @param event The comment added event
     */
    @Async
    @EventListener(FeedCommentAddedEvent.class)
    public void saveNotification(FeedCommentAddedEvent event) {
        var feed = feedReadService.findFeed(event.getFeedId());
        var toMemberId = feed.getMemberId();
        if (notificationSettingService.isCommentAllowed(toMemberId)) {
            NotificationId notificationId = IdFactory.createNotificationId();
            var details = mackDetails(event, feed);
            Notification notification = toNotification(event, notificationId, details, toMemberId);
            notificationService.save(notification);
        }
    }

    /**
     * Makes details for a comment added event and feed.
     *
     * @param event The CommentAddedEvent containing the details of the comment added
     * @param feed  The Feed object associated with the comment
     * @return A HashMap containing the details of the comment added event and feed
     */
    private static NotificationDetails mackDetails(FeedCommentAddedEvent event, Feed feed) {
        return new FeedCommentNotificationDetails(
                feed.getId(),
                feed.getProfileImageUrl(),
                event.getFeedCommentId(),
                event.getCommentContent()
        );
    }
}
