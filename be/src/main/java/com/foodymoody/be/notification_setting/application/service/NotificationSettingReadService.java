package com.foodymoody.be.notification_setting.application.service;

import com.foodymoody.be.common.exception.NotificationSettingNotFoundException;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification_setting.domain.NotificationSettingRepository;
import com.foodymoody.be.notification_setting.domain.NotificationSettingSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationSettingReadService {

    private final NotificationSettingRepository settingRepository;

    @Transactional(readOnly = true)
    public NotificationSettingSummary fetchByMemberId(MemberId toMemberId) {
        return settingRepository.findSummaryByMemberId(toMemberId)
                .orElseThrow(NotificationSettingNotFoundException::new);
    }
}
