package com.foodymoody.be.notification.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedNotificationId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification.domain.FeedNotification;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationJpaRepository extends JpaRepository<FeedNotification, FeedNotificationId>,
        JpaSpecificationExecutor<FeedNotification> {

    @Modifying
    @Query("UPDATE FeedNotification _notification "
            + "SET _notification.isRead = :status, _notification.updatedAt = :updatedAt "
            + "WHERE _notification.id IN :notificationIds AND _notification.toMemberId = :memberId")
    void updateAllStatus(@Param("status") boolean status, @Param("memberId") MemberId memberId,
            @Param("updatedAt") LocalDateTime updatedAt,
            @Param("notificationIds") List<FeedNotificationId> feedNotificationIds);

    @Modifying
    @Query("UPDATE FeedNotification _notification "
            + "SET _notification.isDeleted = true , _notification.updatedAt = :updatedAt "
            + "WHERE _notification.toMemberId = :memberId")
    void deleteAllByMemberId(@Param("memberId") MemberId memberId, @Param("updatedAt") LocalDateTime updatedAt);

    @Modifying
    @Query("UPDATE FeedNotification _notification "
            + "SET _notification.isDeleted = true , _notification.updatedAt = :updatedAt "
            + "WHERE _notification.id IN :notificationIds AND _notification.toMemberId = :memberId")
    void deleteAllByIdIn(@Param("notificationIds") List<FeedNotificationId> feedNotificationIds,
            @Param("updatedAt") LocalDateTime updatedAt, @Param("memberId") MemberId memberId);
}
