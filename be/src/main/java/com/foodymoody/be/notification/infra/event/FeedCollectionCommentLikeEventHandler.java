package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection.application.FeedCollectionReadService;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection_comment.application.FeedCollectionCommentReadService;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionComment;
import com.foodymoody.be.feed_collection_comment_like.domain.FeedCollectionCommentLikeAddedEvent;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import java.util.HashMap;
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
            var notificationId = IdFactory.createNotificationId();
            var feedCollectionId = feedCollectionComment.getFeedCollectionId();
            var feedCollection = feedCollectionService.fetchById(feedCollectionId);
            var details = makeDetails(feedCollectionCommentId, feedCollectionComment, feedCollection);
            var notification = toNotification(event, notificationId, details, toMemberId);
            notificationService.save(notification);
        }
    }

    private static HashMap<String, Object> makeDetails(
            FeedCollectionCommentId feedCollectionCommentId,
            FeedCollectionComment feedCollectionComment,
            FeedCollection feedCollection
    ) {
        var details = new HashMap<String, Object>();
        details.put("feedCollectionCommentId", feedCollectionCommentId.getValue());
        details.put("feedCollectionCommentContent", feedCollectionComment.getContent());
        details.put("feedCollectionId", feedCollection.getId());
        details.put("feedCollectionTitle", feedCollection.getTitle());
        return details;
    }
}
