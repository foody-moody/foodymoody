package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationDetailsFactory.makeDetails;
import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.domain.FeedCollectionAddedEvent;
import com.foodymoody.be.member.application.service.MemberReadService;
import com.foodymoody.be.notification.application.service.NotificationWriteService;
import com.foodymoody.be.notification_setting.application.usecase.NotificationSettingReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionEventHandler {

    private final MemberReadService memberService;
    private final NotificationSettingReadUseCase settingReadUseCase;
    private final NotificationWriteService notificationService;

    @Async
    @Transactional
    @EventListener(FeedCollectionAddedEvent.class)
    public void handle(FeedCollectionAddedEvent event) {
        var fromMemberId = event.getFromMemberId();
        var fromMember = memberService.findById(fromMemberId);
        var followers = fromMember.getFollowers();
        followers.forEach(follower -> {
            var followerId = follower.getId();
            if (settingReadUseCase.isFollowedAllowed(followerId)) {
                saveNotification(event, followerId);
            }
        });
    }

    private void saveNotification(FeedCollectionAddedEvent event, MemberId toMemberId) {
        var feedNotificationId = IdFactory.createNotificationId();
        var details = makeDetails(event);
        var feedNotification = toNotification(event, feedNotificationId, details, toMemberId);
        notificationService.save(feedNotification);
    }

}
