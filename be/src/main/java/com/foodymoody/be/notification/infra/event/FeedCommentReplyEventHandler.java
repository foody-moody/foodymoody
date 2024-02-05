package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentReplyAddedEvent;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.NotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCommentReplyNotificationDetails;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCommentReplyEventHandler {

    private final NotificationWriteService notificationWriteService;
    private final NotificationSettingReadService notificationSettingService;
    private final FeedReadService feedReadService;

    @Async
    @EventListener(FeedCommentReplyAddedEvent.class)
    public void saveNotification(FeedCommentReplyAddedEvent event) {
        var toMemberId = event.getToMemberId();
        if (notificationSettingService.isCommentAllowed(toMemberId)) {
            var feedNotificationId = IdFactory.createNotificationId();
            var feed = feedReadService.findFeed(event.getFeedId());
            var details = makeDetails(event, feed);
            var feedNotification = toNotification(event, feedNotificationId, details, toMemberId);
            notificationWriteService.save(feedNotification);
        }
    }

    private static NotificationDetails makeDetails(FeedCommentReplyAddedEvent event, Feed feed) {
        return new FeedCommentReplyNotificationDetails(
                event.getFeedCommentId(),
                event.getCommentContent(),
                event.getFeedId(),
                feed.getProfileImageUrl(),
                event.getFeedReplyId(),
                event.getReplyContent()
        );
    }
}
