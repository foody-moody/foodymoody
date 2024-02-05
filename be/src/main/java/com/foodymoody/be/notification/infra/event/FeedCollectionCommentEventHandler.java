package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection.application.FeedCollectionReadService;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentAddedEvent;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.NotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionCommentNotificationDetails;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentEventHandler {

    private final FeedCollectionReadService feedCollectionService;
    private final NotificationSettingReadService notificationSettingService;
    private final NotificationWriteService notificationService;

    @Async
    @EventListener(FeedCollectionCommentAddedEvent.class)
    public void handle(FeedCollectionCommentAddedEvent event) {
        var feedCollectionId = event.getFeedCollectionId();
        var feedCollection = feedCollectionService.fetchById(feedCollectionId);
        var toMemberId = feedCollection.getAuthorId();
        if (notificationSettingService.isFeedCollectionCommentAddedAllowed(toMemberId)) {
            var feedNotificationId = IdFactory.createNotificationId();
            var details = makeDetails(event, feedCollectionId, feedCollection);
            var feedNotification = toNotification(event, feedNotificationId, details, toMemberId);
            notificationService.save(feedNotification);
        }
    }

    private static NotificationDetails makeDetails(
            FeedCollectionCommentAddedEvent event, FeedCollectionId feedCollectionId, FeedCollection feedCollection
    ) {
        return new FeedCollectionCommentNotificationDetails(
                feedCollectionId,
                feedCollection.getTitle(),
                feedCollection.getThumbnailUrl(),
                event.getFeedCollectionCommentId(),
                event.getFeedCollectionCommentContent()
        );
    }
}
