package com.foodymoody.be.notification.presentation;

import com.foodymoody.be.common.annotation.MemberId;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.presentation.dto.ChangeAllNotificationStatusRequest;
import com.foodymoody.be.notification.presentation.dto.DeleteNotificationsRequest;
import com.foodymoody.be.notification.presentation.dto.NotificationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NotificationWriteController {

    private final NotificationWriteService notificationWriteService;

    @PutMapping("/api/notifications/{notificationId}")
    public ResponseEntity<Void> changeStatus(@MemberId String memberId, @PathVariable String notificationId,
            @RequestBody NotificationStatus status) {
        notificationWriteService.changeStatus(memberId, notificationId, status.isRead());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/notifications")
    public ResponseEntity<Void> changeAllStatus(@MemberId String memberId,
            @RequestBody ChangeAllNotificationStatusRequest status) {
        notificationWriteService.changeAllStatus(memberId, status.getNotificationIds(), status.isRead());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/notifications/{notificationId}")
    public ResponseEntity<Void> delete(@MemberId String memberId, @PathVariable String notificationId) {
        notificationWriteService.delete(memberId, notificationId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/notifications")
    public ResponseEntity<Void> deleteAll(@MemberId String memberId, @RequestBody(required = false)
    DeleteNotificationsRequest request) {
        if (request != null) {
            notificationWriteService.deleteAll(memberId, request.getNotificationIds());
            return ResponseEntity.noContent().build();
        }
        notificationWriteService.deleteAll(memberId);
        return ResponseEntity.noContent().build();
    }
}
