package com.foodymoody.be.notification.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.notification.application.FeedNotificationWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NotificationWriteController {

    private final FeedNotificationWriteService service;

    @PutMapping("/api/notifications/read-status")
    public ResponseEntity<Void> markAllAsRead(@CurrentMemberId MemberId memberId) {
        service.markAllAsRead(memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/notifications/{notificationId}/read-status")
    public ResponseEntity<Void> markAsRead(
            @CurrentMemberId MemberId memberId,
            @PathVariable NotificationId notificationId
    ) {
        service.markAsRead(memberId, notificationId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/notifications/{notificationId}")
    public ResponseEntity<Void> delete(
            @CurrentMemberId MemberId memberId,
            @PathVariable NotificationId notificationId
    ) {
        service.delete(memberId, notificationId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/notifications/read-status")
    public ResponseEntity<Void> deleteRead(@CurrentMemberId MemberId memberId) {
        service.deleteRead(memberId);
        return ResponseEntity.noContent().build();
    }
}
