package com.foodymoody.be.notification_setting.domain;

import com.foodymoody.be.common.util.IdGenerator;

public class NotificationSettingIdFactory {

    private NotificationSettingIdFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static NotificationSettingId from(String id) {
        return new NotificationSettingId(id);
    }

    public static NotificationSettingId newId() {
        return new NotificationSettingId(IdGenerator.generate());
    }
}
