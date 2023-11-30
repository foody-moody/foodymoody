package com.foodymoody.be.notification.presentation;

import com.foodymoody.be.common.annotation.MemberId;
import com.foodymoody.be.notification.application.NotificationService;
import com.foodymoody.be.notification.presentation.dto.ChangeAllNotificationStatusRequest;
import com.foodymoody.be.notification.presentation.dto.DeleteNotificationsRequest;
import com.foodymoody.be.notification.presentation.dto.NotificationResponse;
import com.foodymoody.be.notification.presentation.dto.NotificationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/api/notifications")
    public ResponseEntity<Slice<NotificationResponse>> requestAll(@MemberId String memberId,
            @PageableDefault Pageable pageable) {
        Slice<NotificationResponse> notifications = notificationService.requestAll(memberId, pageable);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/api/notifications/{notificationId}")
    public ResponseEntity<NotificationResponse> requestOne(@MemberId String memberId,
            @PathVariable String notificationId) {
        NotificationResponse notification = notificationService.requestOne(memberId, notificationId);
        return ResponseEntity.ok(notification);
    }

    @PutMapping("/api/notifications/{notificationId}")
    public ResponseEntity<Void> changeStatus(@MemberId String memberId, @PathVariable String notificationId,
            @RequestBody NotificationStatus status) {
        notificationService.changeStatus(memberId, notificationId, status.isRead());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/notifications")
    public ResponseEntity<Void> changeAllStatus(@MemberId String memberId,
            @RequestBody ChangeAllNotificationStatusRequest status) {
        notificationService.changeAllStatus(memberId, status.getNotificationIds(), status.isRead());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/notifications/{notificationId}")
    public ResponseEntity<Void> delete(@MemberId String memberId, @PathVariable String notificationId) {
        notificationService.delete(memberId, notificationId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/notifications")
    public ResponseEntity<Void> deleteAll(@MemberId String memberId, @RequestBody(required = false)
    DeleteNotificationsRequest request) {
        if (request != null) {
            notificationService.deleteAll(memberId, request.getNotificationIds());
            return ResponseEntity.noContent().build();
        }
        notificationService.deleteAll(memberId);
        return ResponseEntity.noContent().build();
    }
}
