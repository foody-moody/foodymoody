package com.foodymoody.be.notification_setting.application;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification_setting.domain.NotificationSettingRepository;
import com.foodymoody.be.notification_setting.domain.NotificationSettingSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationSettingReadService {

    private final NotificationSettingRepository settingRepository;

    public NotificationSettingSummary request(MemberId memberId) {
        return settingRepository.findSummaryByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("알림 설정이 존재하지 않습니다."));
    }
}
