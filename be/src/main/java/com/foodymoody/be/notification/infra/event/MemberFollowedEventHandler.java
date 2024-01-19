package com.foodymoody.be.notification.infra.event;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.member.application.MemberReadService;
import com.foodymoody.be.member.domain.MemberFollowedEvent;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.infra.event.util.NotificationMapper;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import java.util.HashMap;
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
            boolean myFollowing = fromMember.isMyFollowing(toMemberId);
            var details = makeDetails(myFollowing);
            var notification = NotificationMapper.toNotification(event, notificationId, details, toMemberId);
            notificationService.save(notification);
        }
    }

    private static HashMap<String, Object> makeDetails(boolean myFollowing) {
        var details = new HashMap<String, Object>();
        details.put("myFollowing", myFollowing);
        return details;
    }
}
