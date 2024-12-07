package com.foodymoody.be.notification.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.notification.application.usecase.NotificationReadUseCase;
import com.foodymoody.be.notification.application.usecase.dto.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * The NotificationReadController class handles HTTP requests related to reading notifications.
 */
@RequiredArgsConstructor
@RestController
public class NotificationReadController {

    /**
     * The NotificationReadUseCase is responsible for handling notifications related to reading notifications. It
     * provides methods to request notification responses for a given member ID and notification ID, as well as
     * retrieving all notification responses for a given member ID and pageable configuration.
     */
    private final NotificationReadUseCase useCase;

    /**
     * Retrieves all notification responses for a given member ID and pageable configuration.
     *
     * @param memberId The ID of the member.
     * @param pageable The pageable configuration.
     * @return A slice of notification responses.
     */
    @GetMapping("/api/notifications")
    public ResponseEntity<Slice<NotificationResponse>> requestAll(
            @CurrentMemberId MemberId memberId,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {
        var notificationResponses = useCase.requestAll(memberId, pageable);
        return ResponseEntity.ok(notificationResponses);
    }

    /**
     * Retrieves a notification response for a given member ID and notification ID.
     *
     * @param memberId       The ID of the member.
     * @param notificationId The ID of the notification.
     * @return The notification response.
     */
    @GetMapping("/api/notifications/{notificationId}")
    public ResponseEntity<NotificationResponse> request(
            @CurrentMemberId MemberId memberId,
            @PathVariable NotificationId notificationId
    ) {
        var notification = useCase.request(memberId, notificationId);
        return ResponseEntity.ok(notification);
    }

}
