package com.foodymoody.be.notification.presentation;

import com.foodymoody.be.common.annotation.MemberId;
import com.foodymoody.be.notification.domain.NotificationSummary;
import com.foodymoody.be.notification.infra.usecase.NotificationUseCase;
import com.foodymoody.be.notification.presentation.dto.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NotificationReadController {

    private final NotificationUseCase notificationUseCase;

    @GetMapping("/api/notifications")
    public ResponseEntity<Slice<NotificationSummary>> requestAll(@MemberId String memberId,
            @PageableDefault Pageable pageable) {
        Slice<NotificationSummary> notificationResponses = notificationUseCase.requestAll(memberId, pageable);
        return ResponseEntity.ok(notificationResponses);
    }

    @GetMapping("/api/notifications/{notificationId}")
    public ResponseEntity<NotificationResponse> requestOne(@MemberId String memberId,
            @PathVariable String notificationId) {
        NotificationResponse notification = notificationUseCase.requestOne(memberId, notificationId);
        return ResponseEntity.ok(notification);
    }
}
