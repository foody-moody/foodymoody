package com.foodymoody.be.notification.infra.event;

import com.foodymoody.be.comment.application.ReplyReadService;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import com.foodymoody.be.reply_heart.infra.usecase.ReplyHeartAddedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentReplyHeartEventHandler {

    private final NotificationWriteService notificationWriteService;
    private final ReplyReadService replyReadService;

    @EventListener(ReplyHeartAddedEvent.class)
    public void saveNotification(ReplyHeartAddedEvent event) {
        var feedNotificationId = NotificationIdFactory.newId();
        var reply = replyReadService.fetchById(event.getReplyId());
        var feedNotification = new FeedNotification(
                feedNotificationId,
                event.getMemberId(),
                reply.getMemberId(),
                event.getContent(),
                event.getFeedId(),
                event.getCommentId(),
                event.getNotificationType(),
                false,
                false,
                event.getCreatedAt(),
                event.getCreatedAt()
        );
        notificationWriteService.save(feedNotification);
    }

}
