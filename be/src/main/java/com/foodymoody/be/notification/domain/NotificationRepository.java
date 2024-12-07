package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import java.time.LocalDateTime;
import java.util.Optional;

public interface NotificationRepository {

    /**
     * Counts the number of notifications based on the member ID, deletion status, and read status.
     *
     * @param memberId  The ID of the member
     * @param isDeleted Whether the notification is deleted or not
     * @param isRead    Whether the notification is read or not
     * @return The count of notifications matching the given criteria
     */
    long countByMemberIdAndDeletedAndRead(MemberId memberId, boolean isDeleted, boolean isRead);

    /**
     * Saves a FeedNotification object to the database.
     *
     * @param notification The FeedNotification object to be saved.
     * @return The saved FeedNotification object.
     */
    Notification save(Notification notification);

    /**
     * Retrieves a {@link Notification} object by its ID.
     *
     * @param notificationId The ID of the notification.
     * @return The {@link Optional} containing the retrieved {@link Notification} object.
     */
    Optional<Notification> findById(NotificationId notificationId);

    /**
     * Updates the read status of all notifications for a specific member.
     *
     * @param isRead   The new read status for the notifications (true if read, false if unread)
     * @param memberId The ID of the member
     * @param now      The current timestamp
     */
    void updateAllReadStatus(boolean isRead, MemberId memberId, LocalDateTime now);

    /**
     * Deletes the read status of notifications for a specific member.
     *
     * @param memberId  The ID of the member whose notifications should be deleted.
     * @param updatedAt The timestamp of when the notifications were last updated.
     */
    void deleteRead(MemberId memberId, LocalDateTime updatedAt);

}
