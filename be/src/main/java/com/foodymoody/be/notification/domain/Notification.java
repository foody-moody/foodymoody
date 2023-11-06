package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.event.NotificationType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notification {

    @EmbeddedId
    private NotificationId id;
    private String memberId;
    private String message;
    private NotificationType type;
    private boolean isRead;

    public Notification(NotificationId id, String memberId, String message, NotificationType type, boolean isRead) {
        this.id = id;
        this.memberId = memberId;
        this.message = message;
        this.type = type;
        this.isRead = isRead;
    }

    public void read() {
        this.isRead = true;
    }

    public boolean isSameMember(String memberId) {
        return this.memberId.equals(memberId);
    }

    public void unRead() {
        this.isRead = false;
    }

    public NotificationId getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public NotificationType getType() {
        return type;
    }

    public boolean isRead() {
        return isRead;
    }
}
