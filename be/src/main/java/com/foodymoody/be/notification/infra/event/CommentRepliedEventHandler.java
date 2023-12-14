package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.comment.domain.entity.CommentRepliedAddedEvent;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.notification.application.FeedNotificationWriteService;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentRepliedEventHandler {

    private final FeedNotificationWriteService feedNotificationWriteService;

    @EventListener(CommentRepliedAddedEvent.class)
    public void saveNotification(CommentRepliedAddedEvent event) {
        NotificationId notificationId = NotificationIdFactory.newId();
        FeedNotification feedNotification = toNotification(event, notificationId);
        feedNotificationWriteService.save(feedNotification);
    }
}
