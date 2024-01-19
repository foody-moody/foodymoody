package com.foodymoody.be.notification.infra.usecase;

import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationSummary;
import com.foodymoody.be.notification.infra.usecase.dto.NotificationResponse;
import com.foodymoody.be.notification.infra.usecase.dto.SenderResponse;
import java.util.stream.Collectors;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

/**
 * The NotificationResponseMapper class provides utility methods for mapping NotificationSummary objects to
 * NotificationResponse objects.
 */
public class NotificationResponseMapper {

    /**
     * This class provides utility methods for mapping NotificationSummary objects to NotificationResponse objects.
     */
    private NotificationResponseMapper() {
        throw new AssertionError();
    }

    /**
     * Converts a NotificationSummary object into a NotificationResponse object.
     *
     * @param notificationSummary The NotificationSummary object to convert.
     * @return The converted NotificationResponse object.
     */
    public static NotificationResponse toNotificationResponse(NotificationSummary notificationSummary) {
        SenderResponse sender = SenderResponse.of(
                notificationSummary.getFromMemberId(),
                notificationSummary.getFromNickname(),
                notificationSummary.getFromProfileImageUrl()
        );
        return NotificationResponse.of(
                notificationSummary.getId(),
                sender,
                notificationSummary.getDetails(),
                notificationSummary.getType(),
                notificationSummary.isRead(),
                notificationSummary.getCreatedAt(),
                notificationSummary.getUpdatedAt()
        );
    }

    /**
     * Converts a {@link Notification}, {@link Member}, and {@link Image} into a {@link NotificationResponse} object.
     *
     * @param notification           The feed notification source object.
     * @param fromMember             The source member object.
     * @param fromMemberProfileImage The profile image of the source member.
     * @return The converted {@link NotificationResponse} object.
     */
    public static NotificationResponse toNotificationResponse(
            Notification notification, Member fromMember, Image fromMemberProfileImage
    ) {
        SenderResponse sender = SenderResponse.of(
                fromMember.getId(), fromMember.getNickname(), fromMemberProfileImage.getUrl());
        return NotificationResponse.of(
                notification.getId(),
                sender,
                notification.getDetails(),
                notification.getType(),
                notification.isRead(),
                notification.getCreatedAt(),
                notification.getUpdatedAt()
        );
    }

    /**
     * Converts a list of NotificationSummary objects into a Slice of NotificationResponse objects.
     *
     * @param notificationSummaries The list of NotificationSummary objects to convert.
     * @return A Slice of NotificationResponse objects.
     */
    public static Slice<NotificationResponse> toNotificationResponseList(
            Slice<NotificationSummary> notificationSummaries
    ) {
        var notificationResponses = notificationSummaries.stream()
                .map(NotificationResponseMapper::toNotificationResponse)
                .collect(Collectors.toList());
        return new SliceImpl<>(
                notificationResponses,
                notificationSummaries.getPageable(),
                notificationSummaries.hasNext()
        );
    }
}
