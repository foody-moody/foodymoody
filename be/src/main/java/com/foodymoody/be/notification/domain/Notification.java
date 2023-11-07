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
    private boolean isDeleted;

    public Notification(NotificationId id, String memberId, String message, NotificationType type, boolean isRead,
            boolean isDeleted) {
        this.id = id;
        this.memberId = memberId;
        this.message = message;
        this.type = type;
        this.isRead = isRead;
        this.isDeleted = isDeleted;
    }

    public boolean isSameMember(String memberId) {
        return this.memberId.equals(memberId);
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

    public String getMemberId() {
        return memberId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void changeStatus(boolean isRead, String memberId) {
        checkMemberId(memberId);
        this.isRead = isRead;
    }

    public void delete(String memberId) {
        checkMemberId(memberId);
        this.isDeleted = true;
    }

    private void checkMemberId(String memberId) {
        if (!isSameMember(memberId)) {
            throw new IllegalArgumentException("해당 알림을 수정할 수 없습니다.");
        }
    }
}
