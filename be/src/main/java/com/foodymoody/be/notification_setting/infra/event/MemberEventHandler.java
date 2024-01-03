package com.foodymoody.be.notification_setting.infra.event;

import com.foodymoody.be.member.domain.MemberCreatedEvent;
import com.foodymoody.be.notification_setting.application.NotificationSettingWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberEventHandler {

    private final NotificationSettingWriteService notificationSettingWriteService;

    @EventListener(MemberCreatedEvent.class)
    public void saveNotification(MemberCreatedEvent event) {
        notificationSettingWriteService.save(event.getMemberid());
    }

}
