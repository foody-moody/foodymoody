package com.foodymoody.be.notification_setting.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.NotificationSettingId;
import com.foodymoody.be.notification_setting.domain.NotificationSetting;
import com.foodymoody.be.notification_setting.domain.NotificationSettingSummary;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationSettingJpaRepository extends JpaRepository<NotificationSetting, NotificationSettingId> {

    Optional<NotificationSetting> findByMemberId(String memberId);

    @Query("select _notificationSetting.id.value as id,_notificationSetting.isComment as comment,"
            + "_notificationSetting.isFeed as feed,_notificationSetting.isHeart as heart "
            + "from NotificationSetting _notificationSetting "
            + "where _notificationSetting.memberId = :memberId")
    Optional<NotificationSettingSummary> findSummaryByMemberId(String memberId);
}
