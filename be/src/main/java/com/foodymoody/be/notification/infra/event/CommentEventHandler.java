package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.comment.domain.entity.CommentAddedEvent;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.notification.application.FeedNotificationWriteService;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentEventHandler {

    private final FeedNotificationWriteService feedNotificationWriteService;
    private final FeedReadService feedReadService;

    @EventListener(CommentAddedEvent.class)
    public void saveNotification(CommentAddedEvent event) {
        NotificationId notificationId = NotificationIdFactory.newId();
        Feed feed = feedReadService.findFeed(event.getFeedId());
        FeedNotification feedNotification = toNotification(event, notificationId, feed);
        feedNotificationWriteService.save(feedNotification);
    }
}
