package com.foodymoody.be.notification.infra.event;

import com.foodymoody.be.comment.application.ReplyReadService;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.notification.application.FeedNotificationWriteService;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.reply_heart.infra.usecase.ReplyHeartAddedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentReplyHeartEventHandler {

    private final FeedNotificationWriteService feedNotificationWriteService;
    private final ReplyReadService replyReadService;

    @EventListener(ReplyHeartAddedEvent.class)
    public void saveNotification(ReplyHeartAddedEvent event) {
        var feedNotificationId = IdFactory.createNotificationId();
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
        feedNotificationWriteService.save(feedNotification);
    }

}
