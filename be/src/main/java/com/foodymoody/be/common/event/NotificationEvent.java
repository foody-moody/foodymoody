package com.foodymoody.be.common.event;

import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;

public interface NotificationEvent extends Event {

    MemberId getFromMemberId();

    NotificationType getNotificationType();

    LocalDateTime getCreatedAt();
}
