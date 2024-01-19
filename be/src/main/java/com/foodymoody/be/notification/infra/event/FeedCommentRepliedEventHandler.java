package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentRepliedAddedEvent;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCommentRepliedEventHandler {

    private final NotificationWriteService notificationWriteService;
    private final NotificationSettingReadService notificationSettingService;
    private final FeedReadService feedReadService;

    @Async
    @EventListener(FeedCommentRepliedAddedEvent.class)
    public void saveNotification(FeedCommentRepliedAddedEvent event) {
        var toMemberId = event.getToMemberId();
        if (notificationSettingService.isCommentAllowed(toMemberId)) {
            var feedNotificationId = IdFactory.createNotificationId();
            var feed = feedReadService.findFeed(event.getFeedId());
            var details = makeDetails(event, feed);
            var feedNotification = toNotification(event, feedNotificationId, details, toMemberId);
            notificationWriteService.save(feedNotification);
        }
    }

    private static HashMap<String, Object> makeDetails(FeedCommentRepliedAddedEvent event, Feed feed) {
        var details = new HashMap<String, Object>();
        details.put("commentId", event.getFeedCommentId());
        details.put("commentContent", event.getReplyContent());
        details.put("feedId", event.getFeedId());
        details.put("feedThumbnail", feed.getProfileImageUrl());
        details.put("replyId", event.getFeedReplyId());
        details.put("replyContent", event.getReplyContent());
        return details;
    }
}
