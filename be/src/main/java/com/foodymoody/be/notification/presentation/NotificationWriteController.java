package com.foodymoody.be.notification.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
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

    private final FeedNotificationWriteService feedNotificationWriteService;
    private final NotificationMapper notificationMapper;

    @PutMapping("/api/notifications/read-status")
    public ResponseEntity<Void> markAllAsRead(@CurrentMemberId MemberId memberId) {
        feedNotificationWriteService.markAllAsRead(memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/notifications/{notificationId}/read-status")
    public ResponseEntity<Void> markAsRead(
            @CurrentMemberId MemberId memberId,
            @PathVariable String notificationId
    ) {
        feedNotificationWriteService.markAsRead(
                memberId, notificationMapper.toNotificationID(notificationId));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/notifications/{notificationId}")
    public ResponseEntity<Void> delete(
            @CurrentMemberId MemberId memberId,
            @PathVariable String notificationId
    ) {
        feedNotificationWriteService.delete(memberId, IdFactory.createNotificationId(notificationId));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/notifications/read-status")
    public ResponseEntity<Void> deleteRead(@CurrentMemberId MemberId memberId) {
        feedNotificationWriteService.deleteRead(memberId);
        return ResponseEntity.noContent().build();
    }
}
