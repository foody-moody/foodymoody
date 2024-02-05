package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The Notification class represents a notification.
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notification {

    /**
     * The NotificationId class represents the ID of a notification.
     */
    @Getter
    @EmbeddedId
    private NotificationId id;
    /**
     * The fromMemberId variable represents the ID of the member who sent the notification.
     */
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "from_member_id"))
    private MemberId fromMemberId;
    /**
     * The toMemberId variable represents the ID of the member who received the notification.
     */
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "to_member_id"))
    private MemberId toMemberId;
    /**
     * The details variable represents the details of the notification.
     */
    @Getter
    @Convert(converter = NotificationDetailsConverter.class)
    @Column(columnDefinition = "json")
    private NotificationDetails details;
    /**
     * The type variable represents the type of the notification.
     */
    @Getter
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    /**
     * The isRead variable represents whether the notification has been read.
     */
    @Getter
    private boolean isRead;
    /**
     * The isDeleted variable represents whether the notification has been deleted.
     */
    @Getter
    private boolean isDeleted;
    /**
     * The createdAt variable represents the date and time the notification was created.
     */
    @Getter
    private LocalDateTime createdAt;
    /**
     * The updatedAt variable represents the date and time the notification was last updated.
     */
    @Getter
    private LocalDateTime updatedAt;

    /**
     * Notification is a class representing a notification object. It stores information such as the notification ID,
     * sender member ID, recipient member ID, details, type, read status, delete status, creation timestamp, and last
     * update timestamp.
     *
     * @param id           The ID of the notification
     * @param fromMemberId The ID of the member who sent the notification
     * @param toMemberId   The ID of the member who received the notification
     * @param details      The details of the notification stored as a key-value map
     * @param type         The type of the notification
     * @param isRead       The read status of the notification
     * @param isDeleted    The delete status of the notification
     * @param createdAt    The timestamp when the notification was created
     * @param updatedAt    The timestamp when the notification was last updated
     */
    public Notification(
            NotificationId id, MemberId fromMemberId, MemberId toMemberId, NotificationDetails details,
            NotificationType type, boolean isRead, boolean isDeleted, LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.details = details;
        this.fromMemberId = fromMemberId;
        this.toMemberId = toMemberId;
        this.type = type;
        this.isRead = isRead;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Changes the status of a notification to the specified read status, for the given member and update timestamp.
     *
     * @param isRead    The new read status of the notification
     * @param memberId  The ID of the member for whom the notification status is being changed
     * @param updatedAt The timestamp when the notification status is being updated
     */
    public void changeStatus(boolean isRead, MemberId memberId, LocalDateTime updatedAt) {
        checkMemberId(memberId);
        this.updatedAt = updatedAt;
        this.isRead = isRead;
    }

    /**
     * Deletes the notification for the specified member.
     *
     * @param memberId  The ID of the member who owns the notification to be deleted
     * @param updatedAt The timestamp when the notification is being deleted
     */
    public void delete(MemberId memberId, LocalDateTime updatedAt) {
        checkMemberId(memberId);
        this.isDeleted = true;
        this.updatedAt = updatedAt;
    }

    /**
     * Checks if the given MemberId matches the toMemberId of the notification.
     *
     * @param toMemberId The MemberId to check against the toMemberId of the notification
     * @throws IllegalArgumentException If the toMemberId does not match the notification's toMemberId
     */
    public void checkMemberId(MemberId toMemberId) {
        if (!this.toMemberId.equals(toMemberId)) {
            throw new IllegalArgumentException("해당 알림을 수정할 수 없습니다.");
        }
    }
}
