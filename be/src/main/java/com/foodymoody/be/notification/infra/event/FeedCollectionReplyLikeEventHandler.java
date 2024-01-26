package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationDetailsFactory.makeDetails;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.application.service.FeedCollectionReadService;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection_comment.application.service.FeedCollectionCommentReadService;
import com.foodymoody.be.feed_collection_reply.application.service.FeedCollectionReplyReadService;
import com.foodymoody.be.feed_collection_reply_like.domain.FeedCollectionReplyLikeAddedEvent;
import com.foodymoody.be.notification.application.service.NotificationWriteService;
import com.foodymoody.be.notification.infra.event.util.NotificationMapper;
import com.foodymoody.be.notification_setting.application.service.NotificationSettingReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyLikeEventHandler {

    private final FeedCollectionCommentReadService commentReadService;
    private final NotificationSettingReadService notificationSettingService;
    private final FeedCollectionReadService feedCollectionService;
    private final FeedCollectionReplyReadService replyReadService;
    private final NotificationWriteService notificationService;

    @Async
    @EventListener(FeedCollectionReplyLikeAddedEvent.class)
    public void handle(FeedCollectionReplyLikeAddedEvent event) {
        var commentId = event.getFeedCollectionCommentId();
        var comment = commentReadService.findById(commentId);
        var toMemberId = comment.getMemberId();
        if (notificationSettingService.isFeedCollectionReplyLikeAllowed(toMemberId)) {
            saveNotification(event, toMemberId, comment.getFeedCollectionId());
        }
    }

    private void saveNotification(
            FeedCollectionReplyLikeAddedEvent event,
            MemberId toMemberId,
            FeedCollectionId feedCollectionId
    ) {
        var notificationId = IdFactory.createNotificationId();
        var feedCollection = getFeedCollection(feedCollectionId);
        var feedCollectionReplyContent = getFeedCollectionReplyContent(event);
        var details = makeDetails(
                event,
                feedCollection.getId(),
                feedCollection.getThumbnailUrl(),
                feedCollectionReplyContent
        );
        var notification = NotificationMapper.toNotification(event, notificationId, details, toMemberId);
        notificationService.save(notification);
    }

    private FeedCollection getFeedCollection(FeedCollectionId feedCollectionId) {
        return feedCollectionService.fetchById(feedCollectionId);
    }

    private Content getFeedCollectionReplyContent(FeedCollectionReplyLikeAddedEvent event) {
        var feedCollectionReplyId = event.getFeedCollectionReplyId();
        var reply = replyReadService.fetchById(feedCollectionReplyId);
        return reply.getContent();
    }
}
