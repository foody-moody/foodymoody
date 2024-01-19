package com.foodymoody.be.notification.infra.event;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.application.FeedCollectionReadService;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection_like.domain.FeedCollectionLikeAddedEvent;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.infra.event.util.NotificationMapper;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import java.util.HashMap;
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
        if (notificationSettingService.isFeedCollectionLikeAllowed(toMemberId)) {
            saveNotification(event, feedCollectionId, feedCollection, toMemberId);
        }
    }

    private void saveNotification(
            FeedCollectionLikeAddedEvent event,
            FeedCollectionId feedCollectionId,
            FeedCollection feedCollection,
            MemberId toMemberId
    ) {
        var notificationId = IdFactory.createNotificationId();
        var details = makeDetails(feedCollectionId, feedCollection);
        var notification = NotificationMapper.toNotification(event, notificationId, details, toMemberId);
        notificationService.save(notification);
    }

    private static HashMap<String, Object> makeDetails(
            FeedCollectionId feedCollectionId, FeedCollection feedCollection
    ) {
        var details = new HashMap<String, Object>();
        details.put("feedCollectionId", feedCollectionId);
        details.put("feedCollectionThumbnailUrl", feedCollection.getThumbnailUrl());
        details.put("feedCollectionTitle", feedCollection.getTitle());
        details.put("feedCollectionDescription", feedCollection.getDescription());
        return details;
    }
}
