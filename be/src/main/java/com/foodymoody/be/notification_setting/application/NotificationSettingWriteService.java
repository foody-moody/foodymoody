package com.foodymoody.be.notification_setting.application;

import com.foodymoody.be.member.domain.MemberId;
import com.foodymoody.be.notification_setting.domain.NotificationSetting;
import com.foodymoody.be.notification_setting.domain.NotificationSettingId;
import com.foodymoody.be.notification_setting.domain.NotificationSettingIdFactory;
import com.foodymoody.be.notification_setting.domain.NotificationSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationSettingWriteService {

    private final NotificationSettingRepository notificationSettingRepository;

    @Transactional
    public void save(MemberId memberid) {
        NotificationSettingId notificationSettingId = NotificationSettingIdFactory.newId();
        notificationSettingRepository.save(NotificationSetting.of(notificationSettingId, memberid));
    }
}
