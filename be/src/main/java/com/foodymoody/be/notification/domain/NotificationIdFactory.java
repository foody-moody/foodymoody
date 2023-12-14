package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.common.util.ids.NotificationId;

public class NotificationIdFactory {

    private NotificationIdFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static NotificationId from(String id) {
        return new NotificationId(id);
    }

    public static NotificationId newId() {
        return new NotificationId(IdGenerator.generate());
    }
}
