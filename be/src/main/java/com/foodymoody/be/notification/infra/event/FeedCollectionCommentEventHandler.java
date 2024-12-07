package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationDetailsFactory.makeDetails;
import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.application.service.FeedCollectionReadService;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentAddedEvent;
import com.foodymoody.be.notification.application.service.NotificationWriteService;
import com.foodymoody.be.notification_setting.application.usecase.NotificationSettingReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentEventHandler {

    private final FeedCollectionReadService feedCollectionService;
    private final NotificationSettingReadUseCase settingReadUseCase;
    private final NotificationWriteService notificationService;

    @Async
    @EventListener(FeedCollectionCommentAddedEvent.class)
    public void handle(FeedCollectionCommentAddedEvent event) {
        var feedCollectionId = event.getFeedCollectionId();
        var feedCollection = feedCollectionService.fetchById(feedCollectionId);
        var toMemberId = feedCollection.getAuthorId();
        if (settingReadUseCase.isFeedCollectionCommentAddedAllowed(toMemberId)) {
            saveNotification(event, feedCollectionId, feedCollection, toMemberId);
        }
    }

    private void saveNotification(
            FeedCollectionCommentAddedEvent event,
            FeedCollectionId feedCollectionId,
            FeedCollection feedCollection,
            MemberId toMemberId
    ) {
        var feedNotificationId = IdFactory.createNotificationId();
        var details = makeDetails(event, feedCollection.getThumbnailUrl(), feedCollectionId);
        var feedNotification = toNotification(event, feedNotificationId, details, toMemberId);
        notificationService.save(feedNotification);
    }

}
