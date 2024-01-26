package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationDetailsFactory.makeDetails;
import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.application.service.FeedCollectionReadService;
import com.foodymoody.be.feed_collection_comment.application.service.FeedCollectionCommentReadService;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionComment;
import com.foodymoody.be.feed_collection_comment_like.domain.FeedCollectionCommentLikeAddedEvent;
import com.foodymoody.be.notification.application.service.NotificationWriteService;
import com.foodymoody.be.notification_setting.application.service.NotificationSettingReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentLikeEventHandler {

    private final FeedCollectionCommentReadService feedCollectionCommentService;
    private final NotificationSettingReadService notificationSettingService;
    private final NotificationWriteService notificationService;
    private final FeedCollectionReadService feedCollectionService;

    @Async
    @EventListener(FeedCollectionCommentLikeAddedEvent.class)
    public void handle(FeedCollectionCommentLikeAddedEvent event) {
        var feedCollectionCommentId = event.getFeedCollectionCommentId();
        var feedCollectionComment = feedCollectionCommentService.findById(feedCollectionCommentId);
        var toMemberId = feedCollectionComment.getMemberId();
        if (notificationSettingService.isFeedCollectionCommentLikeAddedAllowed(toMemberId)) {
            saveNotification(event, feedCollectionComment, toMemberId);
        }
    }

    private void saveNotification(
            FeedCollectionCommentLikeAddedEvent event,
            FeedCollectionComment feedCollectionComment,
            MemberId toMemberId
    ) {
        var notificationId = IdFactory.createNotificationId();
        var feedCollectionId = feedCollectionComment.getFeedCollectionId();
        var feedCollection = feedCollectionService.fetchById(feedCollectionId);
        var feedCollectionThumbnailUrl = feedCollection.getThumbnailUrl();
        var details = makeDetails(
                event,
                feedCollectionComment.getContent(),
                feedCollectionId,
                feedCollectionThumbnailUrl
        );
        var notification = toNotification(event, notificationId, details, toMemberId);
        notificationService.save(notification);
    }
}
