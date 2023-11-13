package com.foodymoody.be.common.event;

import java.time.LocalDateTime;

public interface NotificationEvent {

    NotificationType getNotificationType();

    String getMessage();

    String getMemberId();

    LocalDateTime getCreatedAt();
}
