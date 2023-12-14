package com.foodymoody.be.notification.infra.event;

import com.foodymoody.be.comment.application.CommentReadService;
import com.foodymoody.be.comment_heart.infra.usecase.CommentHeartAddedEvent;
import com.foodymoody.be.notification.application.FeedNotificationWriteService;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentHeartEventHandler {

    private final FeedNotificationWriteService feedNotificationWriteService;
    private final CommentReadService commentReadService;

    @EventListener(CommentHeartAddedEvent.class)
    public void saveNotification(CommentHeartAddedEvent event) {
        var feedNotificationId = NotificationIdFactory.newId();
        var comment = commentReadService.fetchById(event.getCommentId());
        var feedNotification = new FeedNotification(
                feedNotificationId,
                event.getMemberId(),
                comment.getMemberId(),
                event.getContent(),
                event.getFeedId(),
                event.getCommentId(),
                event.getNotificationType(),
                false,
                false,
                event.getCreatedAt(),
                event.getCreatedAt()
        );
        feedNotificationWriteService.save(feedNotification);
    }
}
