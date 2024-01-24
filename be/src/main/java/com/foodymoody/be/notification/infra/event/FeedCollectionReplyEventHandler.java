package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.application.FeedCollectionWriteService;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection_comment.application.FeedCollectionCommentReadService;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplyAddedEvent;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.NotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionReplyNotificationDetails;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyEventHandler {

    private final FeedCollectionWriteService feedCollectionService;
    private final FeedCollectionCommentReadService feedCollectionCommentService;
    private final NotificationSettingReadService notificationSettingService;
    private final NotificationWriteService notificationService;

    @Async
    @EventListener(FeedCollectionReplyAddedEvent.class)
    public void handle(FeedCollectionReplyAddedEvent event) {
        var comment = feedCollectionCommentService.findById(event.getToFeedCollectionCommentId());
        MemberId toMemberId = comment.getMemberId();
        if (notificationSettingService.isFeedCollectionCommentRepliedAllowed(toMemberId)) {
            var feedNotificationId = IdFactory.createNotificationId();
            var feedCollectionId = comment.getFeedCollectionId();
            var feed = feedCollectionService.fetchById(feedCollectionId);
            var details = makeDetails(event, feed);
            var feedNotification = toNotification(event, feedNotificationId, details, toMemberId);
            notificationService.save(feedNotification);
        }
    }

    private static NotificationDetails makeDetails(
            FeedCollectionReplyAddedEvent event,
            FeedCollection feed
    ) {
        return new FeedCollectionReplyNotificationDetails(
                feed.getId(),
                feed.getThumbnailUrl(),
                event.getToFeedCollectionCommentId(),
                event.getFeedCollectionReplyId(),
                event.getFeedCollectionReplyContent()
        );
    }
}
