package com.foodymoody.be.notification.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification.infra.usecase.NotificationUseCase;
import com.foodymoody.be.notification.presentation.dto.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NotificationReadController {

    private final NotificationUseCase notificationUseCase;
    private final NotificationMapper notificationMapper;

    @GetMapping("/api/notifications")
    public ResponseEntity<Slice<NotificationResponse>> requestAll(
            @CurrentMemberId MemberId memberId,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {
        var notificationResponses = notificationUseCase.requestAll(memberId, pageable);
        return ResponseEntity.ok(notificationResponses);
    }

    @GetMapping("/api/notifications/{notificationId}")
    public ResponseEntity<NotificationResponse> request(
            @CurrentMemberId MemberId memberId,
            @PathVariable String notificationId
    ) {
        var notificationIdObj = notificationMapper.toNotificationID(notificationId);
        var notification = notificationUseCase.request(memberId, notificationIdObj);
        return ResponseEntity.ok(notification);
    }
}
