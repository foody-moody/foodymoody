package com.foodymoody.be.notification_setting.domain;

import com.foodymoody.be.common.util.ids.MemberId;
import java.util.Optional;

public interface NotificationSettingRepository {

    Optional<NotificationSetting> findByMemberId(MemberId memberId);

    NotificationSetting save(NotificationSetting notificationSetting);

    Optional<NotificationSettingSummary> findSummaryByMemberId(MemberId memberId);
}
