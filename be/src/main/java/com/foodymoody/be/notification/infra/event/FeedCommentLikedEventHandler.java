package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed_comment_like.infra.usecase.FeedCommentLikeAddedEvent;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * The CommentLikedEventHandler class is responsible for handling CommentLikeAddedEvent and saving notifications if
 * comment likes are allowed for the recipient member.
 */
@RequiredArgsConstructor
@Service
public class FeedCommentLikedEventHandler {

    /**
     * The feedNotificationWriteService variable represents an instance of the FeedNotificationWriteService class, which
     * provides methods to manage feed notifications.
     *
     * @see NotificationWriteService
     */
    private final NotificationWriteService notificationWriteService;
    /**
     * The NotificationSettingService class provides methods to manage notification settings.
     *
     * @see NotificationSettingReadService
     */
    private final NotificationSettingReadService notificationSettingService;
    /**
     * The FeedReadService class provides methods for reading feed data from the database.
     *
     * @see FeedReadService
     */
    private final FeedReadService feedReadService;

    /**
     * Saves a notification if comment likes are allowed for the recipient member.
     *
     * @param event The CommentLikeAddedEvent instance.
     */
    @Async
    @EventListener(FeedCommentLikeAddedEvent.class)
    public void saveNotification(FeedCommentLikeAddedEvent event) {
        var toMemberId = event.getToMemberId();
        if (notificationSettingService.isCommentLikedAllowed(toMemberId)) {
            var feedNotificationId = IdFactory.createNotificationId();
            var feed = feedReadService.findFeed(event.getFeedId());
            var details = makeDetails(event, feed);
            var feedNotification = toNotification(event, feedNotificationId, details, toMemberId);
            notificationWriteService.save(feedNotification);
        }
    }

    /**
     * Creates details for a comment like notification event.
     *
     * @param event The CommentLikeAddedEvent instance.
     * @param feed  The Feed instance related to the comment.
     * @return A HashMap containing the details for the notification event.
     */
    private static HashMap<String, Object> makeDetails(FeedCommentLikeAddedEvent event, Feed feed) {
        var details = new HashMap<String, Object>();
        details.put("commentId", event.getFeedCommentId());
        details.put("commentContent", event.getCommentContent());
        details.put("feedId", event.getFeedId());
        details.put("feedThumbnail", feed.getProfileImageUrl());
        return details;
    }
}
