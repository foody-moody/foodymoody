package com.foodymoody.be.notification.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification.application.FeedNotificationWriteService;
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

    private final FeedNotificationWriteService feedNotificationWriteService;
    private final NotificationMapper notificationMapper;

    @PutMapping("/api/notifications/{notificationId}")
    public ResponseEntity<Void> changeStatus(
            @CurrentMemberId MemberId memberId,
            @PathVariable String notificationId,
            @RequestBody NotificationStatus status
    ) {
        feedNotificationWriteService.changeStatus(
                memberId, IdFactory.createNotificationId(notificationId), status.isRead());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/notifications")
    public ResponseEntity<Void> changeAllStatus(
            @CurrentMemberId MemberId memberId,
            @RequestBody ChangeAllNotificationStatusRequest status
    ) {
        var notificationIds = notificationMapper.toNotificationID(status.getNotificationIds());
        feedNotificationWriteService.changeAllStatus(memberId, notificationIds, status.isRead());
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

    @DeleteMapping("/api/notifications")
    public ResponseEntity<Void> deleteAll(
            @CurrentMemberId MemberId memberId,
            @RequestBody(required = false) DeleteNotificationsRequest request
    ) {
        if (request != null) {
            var notificationIds = notificationMapper.toNotificationID(request.getNotificationIds());
            feedNotificationWriteService.deleteAll(memberId, notificationIds);
            return ResponseEntity.noContent().build();
        }
        feedNotificationWriteService.deleteAll(memberId);
        return ResponseEntity.noContent().build();
    }
}
