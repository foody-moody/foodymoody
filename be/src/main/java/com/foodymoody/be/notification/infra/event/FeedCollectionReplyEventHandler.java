package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationDetailsFactory.makeDetails;
import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.application.service.FeedCollectionWriteService;
import com.foodymoody.be.feed_collection_comment.application.service.FeedCollectionCommentReadService;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionComment;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplyAddedEvent;
import com.foodymoody.be.notification.application.service.NotificationWriteService;
import com.foodymoody.be.notification_setting.application.service.NotificationSettingReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyEventHandler {

    private final FeedCollectionWriteService feedCollectionService;
    private final FeedCollectionCommentReadService feedCollectionCommentService;
    private final NotificationSettingReadService notificationSettingService;
    private final NotificationWriteService notificationService;

    @Async
    @EventListener(FeedCollectionReplyAddedEvent.class)
    public void handle(FeedCollectionReplyAddedEvent event) {
        var comment = feedCollectionCommentService.findById(event.getToFeedCollectionCommentId());
        var toMemberId = comment.getMemberId();
        if (notificationSettingService.isFeedCollectionCommentRepliedAllowed(toMemberId)) {
            saveNotification(event, comment, toMemberId);
        }
    }

    private void saveNotification(
            FeedCollectionReplyAddedEvent event,
            FeedCollectionComment comment,
            MemberId toMemberId
    ) {
        var feedNotificationId = IdFactory.createNotificationId();
        var feedCollectionId = comment.getFeedCollectionId();
        var feed = feedCollectionService.fetchById(feedCollectionId);
        var details = makeDetails(event, feed.getId(), feed.getThumbnailUrl());
        var feedNotification = toNotification(event, feedNotificationId, details, toMemberId);
        notificationService.save(feedNotification);
    }
}
