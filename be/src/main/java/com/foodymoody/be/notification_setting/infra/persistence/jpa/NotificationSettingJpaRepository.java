package com.foodymoody.be.notification_setting.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationSettingId;
import com.foodymoody.be.notification_setting.domain.NotificationSetting;
import com.foodymoody.be.notification_setting.domain.NotificationSettingSummary;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationSettingJpaRepository extends JpaRepository<NotificationSetting, NotificationSettingId> {

    Optional<NotificationSetting> findByMemberId(MemberId memberId);

    @Query("select " +
            "_notificationSetting.isFeedComment as feedComment" +
            ",_notificationSetting.isCollectionComment as collectionComment" +
            ",_notificationSetting.isCollectionLike as collectionLike" +
            ",_notificationSetting.isFeedLike as feedLike,_notificationSetting.isFollow as follow" +
            ",_notificationSetting.isCommentLike as commentLike " +
            "from NotificationSetting _notificationSetting " +
            "where _notificationSetting.memberId = :memberId")
    Optional<NotificationSettingSummary> findSummaryByMemberId(MemberId memberId);

}
