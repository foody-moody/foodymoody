package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.common.util.ids.FeedNotificationId;

public class NotificationIdFactory {

    private NotificationIdFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static FeedNotificationId from(String id) {
        return new FeedNotificationId(id);
    }

    public static FeedNotificationId newId() {
        return new FeedNotificationId(IdGenerator.generate());
    }
}
