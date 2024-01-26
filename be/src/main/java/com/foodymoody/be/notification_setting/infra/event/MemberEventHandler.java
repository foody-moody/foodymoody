package com.foodymoody.be.notification_setting.infra.event;

import com.foodymoody.be.member.domain.MemberCreatedEvent;
import com.foodymoody.be.notification_setting.application.service.NotificationSettingWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberEventHandler {

    private final NotificationSettingWriteService notificationSettingWriteService;

    @Async
    @EventListener(MemberCreatedEvent.class)
    @Transactional
    public void saveNotification(MemberCreatedEvent event) {
        notificationSettingWriteService.save(event.getMemberid());
    }

}
