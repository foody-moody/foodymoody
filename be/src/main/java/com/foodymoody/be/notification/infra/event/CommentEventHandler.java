package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.comment.domain.entity.CommentAddedEvent;
import com.foodymoody.be.common.util.ids.FeedNotificationId;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentEventHandler {

    private final NotificationWriteService notificationWriteService;
    private final FeedReadService feedReadService;

    @EventListener(CommentAddedEvent.class)
    public void saveNotification(CommentAddedEvent event) {
        FeedNotificationId feedNotificationId = NotificationIdFactory.newId();
        Feed feed = feedReadService.findFeed(event.getFeedId());
        FeedNotification feedNotification = toNotification(event, feedNotificationId, feed);
        notificationWriteService.save(feedNotification);
    }
}
