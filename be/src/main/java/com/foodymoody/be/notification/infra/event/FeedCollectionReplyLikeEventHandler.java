package com.foodymoody.be.notification.infra.event;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection.application.FeedCollectionReadService;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection_comment.application.FeedCollectionCommentReadService;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionComment;
import com.foodymoody.be.feed_collection_reply.application.FeedCollectionReplyReadService;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReply;
import com.foodymoody.be.feed_collection_reply_like.domain.FeedCollectionReplyLikeAddedEvent;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.NotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionReplyLikeNotificationDetails;
import com.foodymoody.be.notification.infra.event.util.NotificationMapper;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
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
            var notificationId = IdFactory.createNotificationId();
            var feedCollectionId = comment.getFeedCollectionId();
            var feedCollection = feedCollectionService.fetchById(feedCollectionId);
            var feedCollectionReplyId = event.getFeedCollectionReplyId();
            var reply = replyReadService.fetchById(feedCollectionReplyId);
            var details = makeDetails(feedCollection, comment, reply);
            var notification = NotificationMapper.toNotification(event, notificationId, details, toMemberId);
            notificationService.save(notification);
        }
    }

    private static NotificationDetails makeDetails(
            FeedCollection feedCollection,
            FeedCollectionComment comment,
            FeedCollectionReply reply
    ) {
        return new FeedCollectionReplyLikeNotificationDetails(
                feedCollection.getId(),
                feedCollection.getTitle(),
                feedCollection.getDescription(),
                comment.getId(),
                comment.getContent(),
                reply.getId(),
                reply.getContent()
        );
    }
}
