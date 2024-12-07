package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import java.time.LocalDateTime;
import javax.persistence.Convert;

/**
 * The NotificationSummary interface represents a summary of a notification.
 */
public interface NotificationSummary {

    /**
     * Retrieves the ID of the notification.
     *
     * @return The ID of the notification
     */
    NotificationId getId();

    /**
     * Retrieves the MemberId of the member who sent the notification.
     *
     * @return The MemberId of the member who sent the notification
     */
    MemberId getFromMemberId();

    /**
     * Retrieves the nickname of the member who sent the notification.
     *
     * @return The nickname of the member who sent the notification
     */
    String getFromNickname();

    /**
     * Retrieves the profile image URL of the member who sent the notification.
     *
     * @return The profile image URL of the member who sent the notification
     */
    String getFromProfileImageUrl();

    /**
     * Retrieves the details of the notification.
     *
     * @return A map containing the details of the notification.
     */
    @Convert(converter = NotificationDetailsConverter.class)
    NotificationDetails getDetails();

    /**
     * Check if the notification has been read.
     *
     * @return {@code true} if the notification has been read, {@code false} otherwise.
     */
    boolean isRead();

    /**
     * Retrieves the type of the notification.
     *
     * @return The type of the notification.
     */
    NotificationType getType();

    /**
     * Retrieves the creation time of the notification.
     *
     * @return The creation time of the notification.
     */
    LocalDateTime getCreatedAt();

    /**
     * Retrieves the updated time of the notification.
     *
     * @return The updated time of the notification.
     */
    LocalDateTime getUpdatedAt();

}
