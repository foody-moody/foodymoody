package com.foodymoody.be.notification.application;

import com.foodymoody.be.common.exception.FeedNotificationNotFoundException;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The FeedNotificationWriteService class provides methods to manage feed notifications.
 */
@RequiredArgsConstructor
@Service
public class NotificationWriteService {

    /**
     * Represents a repository for managing notifications.
     */
    private final NotificationRepository notificationRepository;

    /**
     * Saves a {@link Notification} object to the database.
     *
     * @param notification The FeedNotification object to be saved.
     */
    @Transactional
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    /**
     * Deletes a notification for a specific member.
     *
     * @param memberId       The ID of the member
     * @param notificationId The ID of the notification to be deleted
     */
    @Transactional
    public void delete(MemberId memberId, NotificationId notificationId) {
        var feedNotification = this.getNotification(notificationId);
        var updatedAt = LocalDateTime.now();
        feedNotification.delete(memberId, updatedAt);
    }

    /**
     * Retrieves a single notification by its ID.
     *
     * @param notificationId The ID of the notification
     * @return The retrieved notification
     * @throws IllegalArgumentException If the notification does not exist
     */
    public Notification getNotification(NotificationId notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(FeedNotificationNotFoundException::new);
    }

    /**
     * Reads a feed notification and updates its status to "read".
     *
     * @param notificationId The ID of the notification
     * @param toMemberId     The ID of the member
     * @return The feed notification that has been read
     * @throws IllegalArgumentException If the notification does not exist or if the member is not the intended
     *                                  recipient of the notification
     */
    @Transactional
    public Notification read(NotificationId notificationId, MemberId toMemberId) {
        var feedNotification = this.getNotification(notificationId);
        var updatedAt = LocalDateTime.now();
        feedNotification.changeStatus(true, toMemberId, updatedAt);
        return feedNotification;
    }

    /**
     * Marks all notifications as read for the given member.
     *
     * @param memberId The ID of the member
     */
    @Transactional
    public void markAllAsRead(MemberId memberId) {
        var updatedAt = LocalDateTime.now();
        notificationRepository.updateAllReadStatus(true, memberId, updatedAt);
    }

    /**
     * Deletes all read feed notifications for a specific member.
     *
     * @param memberId the ID of the member whose read notifications should be deleted
     */
    @Transactional
    public void deleteRead(MemberId memberId) {
        var updatedAt = LocalDateTime.now();
        notificationRepository.deleteRead(memberId, updatedAt);
    }

    /**
     * Marks a notification as read for the given member.
     *
     * @param memberId       The ID of the member
     * @param notificationID The ID of the notification
     */
    @Transactional
    public void markAsRead(MemberId memberId, NotificationId notificationID) {
        var feedNotification = this.getNotification(notificationID);
        LocalDateTime updatedAt = LocalDateTime.now();
        feedNotification.changeStatus(true, memberId, updatedAt);
    }
}
