package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationDetailsFactory.makeDetails;
import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.service.FeedReadService;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentReplyAddedEvent;
import com.foodymoody.be.notification.application.service.NotificationWriteService;
import com.foodymoody.be.notification_setting.application.service.NotificationSettingReadService;
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
            saveNotification(event, toMemberId);
        }
    }

    private void saveNotification(FeedCommentReplyAddedEvent event, MemberId toMemberId) {
        var feedNotificationId = IdFactory.createNotificationId();
        var feed = feedReadService.findFeed(event.getFeedId());
        var details = makeDetails(event, feed.getProfileImageUrl());
        var feedNotification = toNotification(event, feedNotificationId, details, toMemberId);
        notificationWriteService.save(feedNotification);
    }
}
