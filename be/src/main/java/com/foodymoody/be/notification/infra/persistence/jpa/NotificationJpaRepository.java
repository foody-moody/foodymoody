package com.foodymoody.be.notification.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationSummary;
import io.lettuce.core.dynamic.annotation.Param;
import java.time.LocalDateTime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NotificationJpaRepository extends JpaRepository<Notification, NotificationId> {

    /**
     * Updates the read status of all feed notifications for a specific member.
     *
     * @param isRead    the new read status of the notifications (true for read, false for unread)
     * @param memberId  the ID of the member whose notifications should be updated
     * @param updatedAt the date and time of the update
     */
    @Modifying
    @Query("UPDATE Notification _notification " +
            "SET _notification.isRead = :isRead, _notification.updatedAt = :updatedAt " +
            "WHERE _notification.toMemberId = :memberId")
    void updateAllReadStatus(
            @Param("isRead") boolean isRead,
            @Param("memberId") MemberId memberId,
            @Param("updatedAt") LocalDateTime updatedAt
    );

    /**
     * Deletes all read feed notifications for a specific member.
     *
     * @param memberId  the ID of the member whose read notifications should be deleted
     * @param updatedAt the date and time of the update
     */
    @Modifying
    @Query("UPDATE Notification _notification " +
            "SET _notification.isDeleted = true, _notification.updatedAt = :updatedAt " +
            "WHERE _notification.isRead = true AND _notification.toMemberId = :memberId")
    void deleteRead(
            @Param("memberId") MemberId memberId,
            @Param("updatedAt") LocalDateTime updatedAt
    );

    /**
     * Retrieves a slice of notification summaries for a given member ID and pageable information.
     *
     * @param memberId the ID of the member whose notifications should be retrieved
     * @param pageable the pagination information
     * @return a {@link Slice} of {@link NotificationSummary} objects
     */
    @Query("SELECT _notification.id as id " +
            ", _fromMember.id as fromMemberId " +
            ", _fromMember.nickname as fromNickname " +
            ", _memberImage.url as fromProfileImageUrl " +
            ", _notification.details as details " +
            ", _notification.isRead as read " +
            ", _notification.type as type " +
            ", _notification.createdAt as createdAt " +
            ", _notification.updatedAt as updatedAt " +
            "FROM Notification _notification " +
            "JOIN Member _fromMember ON _notification.fromMemberId = _fromMember.id " +
            "JOIN Image _memberImage ON _fromMember.profileImage.id = _memberImage.id " +
            "WHERE _notification.toMemberId = :memberId AND _notification.isDeleted = false " +
            "ORDER BY _notification.createdAt DESC")
    Slice<NotificationSummary> findAllSummaryByMemberId(MemberId memberId, Pageable pageable);

    /**
     * Counts the number of notifications for a given member ID, deleted status, and read status.
     *
     * @param toMemberId The ID of the member
     * @param isDeleted  The deleted status of the notification
     * @param isRead     The read status of the notification
     * @return The count of notifications
     */
    long countByToMemberIdAndIsDeletedAndIsRead(MemberId toMemberId, boolean isDeleted, boolean isRead);

}
