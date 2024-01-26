package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationDetailsFactory.makeDetails;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.application.service.FeedCollectionReadService;
import com.foodymoody.be.feed_collection_like.domain.FeedCollectionLikeAddedEvent;
import com.foodymoody.be.notification.application.service.NotificationWriteService;
import com.foodymoody.be.notification.infra.event.util.NotificationMapper;
import com.foodymoody.be.notification_setting.application.service.NotificationSettingReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionLikeAddedEventHandler {

    private final FeedCollectionReadService feedCollectionService;
    private final NotificationSettingReadService notificationSettingService;
    private final NotificationWriteService notificationService;

    @Async
    @EventListener(FeedCollectionLikeAddedEvent.class)
    public void handle(FeedCollectionLikeAddedEvent event) {
        var feedCollectionId = event.getFeedCollectionId();
        var feedCollection = feedCollectionService.fetchById(feedCollectionId);
        var toMemberId = feedCollection.getAuthorId();
        var feedCollectionThumbnailUrl = feedCollection.getThumbnailUrl();
        if (notificationSettingService.isFeedCollectionLikeAllowed(toMemberId)) {
            saveNotification(event, toMemberId, feedCollectionThumbnailUrl);
        }
    }

    private void saveNotification(
            FeedCollectionLikeAddedEvent event,
            MemberId toMemberId,
            String thumbnailUrl
    ) {
        var notificationId = IdFactory.createNotificationId();
        var details = makeDetails(event, thumbnailUrl);
        var notification = NotificationMapper.toNotification(event, notificationId, details, toMemberId);
        notificationService.save(notification);
    }
}
