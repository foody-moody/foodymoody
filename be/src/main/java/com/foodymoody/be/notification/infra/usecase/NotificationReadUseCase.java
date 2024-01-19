package com.foodymoody.be.notification.infra.usecase;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.image.application.ImageService;
import com.foodymoody.be.member.application.MemberQueryService;
import com.foodymoody.be.notification.application.NotificationSummaryReadService;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.infra.usecase.dto.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The NotificationReadUseCase class represents a use case for reading and retrieving notifications.
 */
@RequiredArgsConstructor
@Service
public class NotificationReadUseCase {

    /**
     * Represents the write service for managing notifications.
     *
     * @see NotificationWriteService
     */
    private final NotificationWriteService notificationWriteService;
    /**
     * Represents the image service for managing images.
     *
     * @see ImageService
     */
    private final ImageService imageService;
    /**
     * Represents the member query service for managing members.
     *
     * @see MemberQueryService
     */
    private final MemberQueryService memberQueryService;
    /**
     * Represents the notification summary read service for managing notification summaries.
     *
     * @see NotificationSummaryReadService
     */
    private final NotificationSummaryReadService notificationSummaryReadService;

    /**
     * Requests a notification response for a given member ID and notification ID.
     *
     * @param memberId       The ID of the member.
     * @param notificationId The ID of the notification.
     * @return The notification response.
     */
    @Transactional
    public NotificationResponse request(MemberId memberId, NotificationId notificationId) {
        var feedNotification = notificationWriteService.read(notificationId, memberId);
        var fromMemberId = feedNotification.getFromMemberId();
        var fromMember = memberQueryService.findById(fromMemberId);
        var fromMemberProfileImage = imageService.findById(fromMember.getProfileImageId());
        return NotificationResponseMapper.toNotificationResponse(feedNotification, fromMember, fromMemberProfileImage);
    }

    /**
     * Retrieves all notification responses for a given member ID and pageable configuration.
     *
     * @param memberId The ID of the member.
     * @param pageable The pageable configuration.
     * @return A slice of notification responses.
     */
    @Transactional(readOnly = true)
    public Slice<NotificationResponse> requestAll(MemberId memberId, Pageable pageable) {
        var notificationSummaries = notificationSummaryReadService.requestAll(
                memberId,
                pageable
        );
        return NotificationResponseMapper.toNotificationResponseList(notificationSummaries);
    }
}
