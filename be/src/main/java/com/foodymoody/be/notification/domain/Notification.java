package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.event.NotificationType;
import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Notification(NotificationId id, String memberId, String message, NotificationType type, boolean isRead,
            boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.memberId = memberId;
        this.message = message;
        this.type = type;
        this.isRead = isRead;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public void changeStatus(boolean isRead, String memberId, LocalDateTime updatedAt) {
        checkMemberId(memberId);
        this.updatedAt = updatedAt;
        this.isRead = isRead;
    }

    public void delete(String memberId, LocalDateTime updatedAt) {
        checkMemberId(memberId);
        this.isDeleted = true;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    private void checkMemberId(String memberId) {
        if (!isSameMember(memberId)) {
            throw new IllegalArgumentException("해당 알림을 수정할 수 없습니다.");
        }
    }
}
