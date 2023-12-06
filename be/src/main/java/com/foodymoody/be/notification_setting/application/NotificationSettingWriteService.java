package com.foodymoody.be.notification_setting.application;

import com.foodymoody.be.common.util.ids.NotificationSettingId;
import com.foodymoody.be.member.domain.MemberId;
import com.foodymoody.be.notification_setting.application.dto.NotificationSettingUpdateRequest;
import com.foodymoody.be.notification_setting.domain.NotificationSetting;
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

    @Transactional
    public void update(String memberId, NotificationSettingUpdateRequest request) {
        NotificationSetting notificationSetting = getNotificationSettingByMemberId(memberId);
        notificationSetting.update(request.isHeart(), request.isComment(), request.isFeed());
    }

    private NotificationSetting getNotificationSettingByMemberId(String memberId) {
        return notificationSettingRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("알림 설정이 존재하지 않습니다."));
    }
}
