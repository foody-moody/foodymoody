package com.foodymoody.be.notification.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.notification.application.service.NotificationWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The NotificationWriteController is responsible for handling write operations related to feed notifications. It
 * provides methods for marking notifications as read, deleting notifications, and managing read status for members.
 */
@RequiredArgsConstructor
@RestController
public class NotificationWriteController {

    /**
     * Represents the feed notification write service. This service is responsible for saving, deleting, and marking
     * notifications as read for members.
     */
    private final NotificationWriteService service;

    /**
     * Marks all notifications as read for the given member.
     *
     * @param memberId The ID of the member
     * @return A response entity with no content
     */
    @PutMapping("/api/notifications/read-status")
    public ResponseEntity<Void> markAllAsRead(@CurrentMemberId MemberId memberId) {
        service.markAllAsRead(memberId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Marks a notification as read for the given member.
     *
     * @param memberId       The ID of the member
     * @param notificationId The ID of the notification
     * @return A response entity with no content
     */
    @PutMapping("/api/notifications/{notificationId}/read-status")
    public ResponseEntity<Void> markAsRead(
            @CurrentMemberId MemberId memberId,
            @PathVariable NotificationId notificationId
    ) {
        service.markAsRead(memberId, notificationId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a notification for a specific member.
     *
     * @param memberId The ID of the member
     * @param notificationId The ID of the notification to be deleted
     * @return A response entity with no content
     */
    @DeleteMapping("/api/notifications/{notificationId}")
    public ResponseEntity<Void> delete(
            @CurrentMemberId MemberId memberId,
            @PathVariable NotificationId notificationId
    ) {
        service.delete(memberId, notificationId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes all read notifications for the given member.
     *
     * @param memberId The ID of the member
     * @return A response entity with no content
     */
    @DeleteMapping("/api/notifications/read-status")
    public ResponseEntity<Void> deleteRead(@CurrentMemberId MemberId memberId) {
        service.deleteRead(memberId);
        return ResponseEntity.noContent().build();
    }

}
