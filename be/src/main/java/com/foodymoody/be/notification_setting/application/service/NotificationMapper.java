package com.foodymoody.be.notification_setting.application.service;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationSettingId;
import com.foodymoody.be.notification_setting.domain.NotificationSetting;

public class NotificationMapper {

    private NotificationMapper() {
        throw new AssertionError();
    }

    public static NotificationSetting toNotificationSetting(
            MemberId memberid,
            NotificationSettingId notificationSettingId
    ) {
        return new NotificationSetting(
                notificationSettingId,
                memberid,
                true,
                true,
                true,
                true,
                true,
                true
        );
    }
}
