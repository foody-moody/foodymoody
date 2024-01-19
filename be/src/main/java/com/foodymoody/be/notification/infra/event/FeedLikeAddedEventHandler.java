package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed_like.domain.entity.FeedLikeAddedEvent;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedLikeAddedEventHandler {

    private final FeedReadService feedService;
    private final NotificationSettingReadService notificationSettingService;
    private final NotificationWriteService notificationService;

    @Async
    @EventListener(FeedLikeAddedEvent.class)
    public void handle(FeedLikeAddedEvent event) {
        var feedId = event.getFeedId();
        var feed = feedService.findFeed(feedId);
        var toMemberId = feed.getMemberId();
        if (notificationSettingService.isFeedLikedAllowed(toMemberId)) {
            var notificationId = IdFactory.createNotificationId();
            var details = mackDetails(feedId, feed);
            var notification = toNotification(event, notificationId, details, toMemberId);
            notificationService.save(notification);
        }
    }

    private static HashMap<String, Object> mackDetails(FeedId feedId, Feed feed) {
        var details = new HashMap<String, Object>();
        details.put("feedId", feedId);
        details.put("feedImageUrl", feed.getProfileImageUrl());
        return details;
    }
}
