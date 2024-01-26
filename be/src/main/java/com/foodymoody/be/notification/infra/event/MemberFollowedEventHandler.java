package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationDetailsFactory.makeDetails;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.member.application.service.MemberReadService;
import com.foodymoody.be.member.domain.MemberFollowedEvent;
import com.foodymoody.be.notification.application.service.NotificationWriteService;
import com.foodymoody.be.notification.infra.event.util.NotificationMapper;
import com.foodymoody.be.notification_setting.application.service.NotificationSettingReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberFollowedEventHandler {

    private final MemberReadService memberQueryService;
    private final NotificationSettingReadService notificationSettingService;
    private final NotificationWriteService notificationService;

    @Async
    @Transactional
    @EventListener(MemberFollowedEvent.class)
    public void handle(MemberFollowedEvent event) {
        var toMemberId = event.getToMemberId();
        if (notificationSettingService.isMemberFollowedEventEnabled(toMemberId)) {
            var notificationId = IdFactory.createNotificationId();
            var fromMember = memberQueryService.findById(event.getFromMemberId());
            boolean isFollowed = fromMember.isMyFollowing(toMemberId);
            var details = makeDetails(isFollowed);
            var notification = NotificationMapper.toNotification(event, notificationId, details, toMemberId);
            notificationService.save(notification);
        }
    }
}
