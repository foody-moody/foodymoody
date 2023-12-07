package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.comment.domain.entity.CommentRepliedAddedEvent;
import com.foodymoody.be.common.util.ids.FeedNotificationId;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentRepliedEventHandler {

    private final NotificationWriteService notificationWriteService;

    @EventListener(CommentRepliedAddedEvent.class)
    public void saveNotification(CommentRepliedAddedEvent event) {
        FeedNotificationId feedNotificationId = NotificationIdFactory.newId();
        FeedNotification feedNotification = toNotification(event, feedNotificationId);
        notificationWriteService.save(feedNotification);
    }
}
