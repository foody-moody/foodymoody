package com.foodymoody.be.notification_setting.domain;

import java.util.Optional;

public interface NotificationSettingRepository {

    Optional<NotificationSetting> findByMemberId(String memberId);

    NotificationSetting save(NotificationSetting notificationSetting);

    Optional<NotificationSettingSummary> findSummaryByMemberId(String memberId);
}
