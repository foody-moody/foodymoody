package com.foodymoody.be.notification_setting.infra.persistence;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification_setting.domain.NotificationSetting;
import com.foodymoody.be.notification_setting.domain.NotificationSettingRepository;
import com.foodymoody.be.notification_setting.domain.NotificationSettingSummary;
import com.foodymoody.be.notification_setting.infra.persistence.jpa.NotificationSettingJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class NotificationSettingRepositoryImpl implements NotificationSettingRepository {

    private final NotificationSettingJpaRepository notificationSettingJpaRepository;

    @Override
    public Optional<NotificationSetting> findByMemberId(MemberId memberId) {
        return notificationSettingJpaRepository.findByMemberId(memberId);
    }

    @Override
    public NotificationSetting save(NotificationSetting notificationSetting) {
        return notificationSettingJpaRepository.save(notificationSetting);
    }

    @Override
    public Optional<NotificationSettingSummary> findSummaryByMemberId(MemberId memberId) {
        return notificationSettingJpaRepository.findSummaryByMemberId(memberId);
    }
}
