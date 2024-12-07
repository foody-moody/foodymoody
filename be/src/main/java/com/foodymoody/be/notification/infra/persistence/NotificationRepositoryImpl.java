package com.foodymoody.be.notification.infra.persistence;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationRepository;
import com.foodymoody.be.notification.infra.persistence.jpa.NotificationJpaRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * NotificationRepositoryImpl is an implementation of the NotificationRepository interface. It provides methods to
 * interact with the NotificationJpaRepository for performing CRUD operations on FeedNotification objects. The
 * implementation is responsible for counting the number of notifications, saving a notification, finding a notification
 * by ID, updating the read status of notifications, and deleting read notifications.
 */
@RequiredArgsConstructor
@Repository
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationJpaRepository notificationJpaRepository;

    /**
     * Counts the number of notifications for a given member ID, deleted status, and read status.
     *
     * @param memberId  the ID of the member
     * @param isDeleted the deleted status of the notification
     * @param isRead    the read status of the notification
     * @return the count of notifications
     */
    @Override
    public long countByMemberIdAndDeletedAndRead(MemberId memberId, boolean isDeleted, boolean isRead) {
        return notificationJpaRepository.countByToMemberIdAndIsDeletedAndIsRead(memberId, isDeleted, isRead);
    }

    /**
     * Saves a FeedNotification object.
     *
     * @param notification the FeedNotification object to be saved
     * @return the saved FeedNotification object
     */
    @Override
    public Notification save(Notification notification) {
        return notificationJpaRepository.save(notification);
    }

    /**
     * Finds a feed notification by its ID.
     *
     * @param notificationId the ID of the feed notification to find
     * @return an Optional containing the found FeedNotification, or an empty Optional if not found
     */
    @Override
    public Optional<Notification> findById(NotificationId notificationId) {
        return notificationJpaRepository.findById(notificationId);
    }

    /**
     * Updates the read status of all feed notifications for a specific member.
     *
     * @param isRead   the new read status of the notifications (true for read, false for unread)
     * @param memberId the ID of the member whose notifications should be updated
     * @param now      the date and time of the update
     */
    @Override
    public void updateAllReadStatus(boolean isRead, MemberId memberId, LocalDateTime now) {
        notificationJpaRepository.updateAllReadStatus(isRead, memberId, now);
    }

    /**
     * Deletes all read feed notifications for a specific member.
     *
     * @param memberId  the ID of the member whose read notifications should be deleted
     * @param updatedAt the date and time of the update
     */
    @Override
    public void deleteRead(MemberId memberId, LocalDateTime updatedAt) {
        notificationJpaRepository.deleteRead(memberId, updatedAt);
    }

}
