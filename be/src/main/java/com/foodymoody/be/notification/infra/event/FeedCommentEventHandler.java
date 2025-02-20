package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationDetailsFactory.mackDetails;
import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.service.FeedReadService;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentAddedEvent;
import com.foodymoody.be.notification.application.service.NotificationWriteService;
import com.foodymoody.be.notification_setting.application.usecase.NotificationSettingReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCommentEventHandler {

    private final NotificationWriteService notificationService;
    private final FeedReadService feedReadService;
    private final NotificationSettingReadUseCase settingReadUseCase;

    @Async
    @EventListener(FeedCommentAddedEvent.class)
    public void saveNotification(FeedCommentAddedEvent event) {
        var feed = feedReadService.findFeed(event.getFeedId());
        var toMemberId = feed.getMemberId();
        if (settingReadUseCase.isCommentAllowed(toMemberId)) {
            saveNotification(event, feed, toMemberId);
        }
    }

    private void saveNotification(FeedCommentAddedEvent event, Feed feed, MemberId toMemberId) {
        var notificationId = IdFactory.createNotificationId();
        var details = mackDetails(event, feed.getId(), feed.getProfileImageUrl());
        var notification = toNotification(event, notificationId, details, toMemberId);
        notificationService.save(notification);
    }

}
