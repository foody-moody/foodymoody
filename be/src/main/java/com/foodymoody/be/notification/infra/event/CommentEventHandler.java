package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.comment.domain.entity.CommentAddedEvent;
import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.service.FeedService;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.domain.FeedNotificationId;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentEventHandler {

    private final NotificationWriteService notificationWriteService;
    private final FeedService feedService;

    @EventListener(CommentAddedEvent.class)
    public void saveNotification(CommentAddedEvent event) {
        FeedNotificationId feedNotificationId = NotificationIdFactory.newId();
        Feed feed = feedService.findFeed(event.getFeedId());
        FeedNotification feedNotification = toNotification(event, feedNotificationId, feed);
        notificationWriteService.save(feedNotification);
    }
}
