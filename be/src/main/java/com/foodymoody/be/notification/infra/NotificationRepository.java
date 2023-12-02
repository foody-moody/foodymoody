package com.foodymoody.be.notification.infra;

import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationRepository extends JpaRepository<Notification, NotificationId>,
        JpaSpecificationExecutor<Notification> {

    @Modifying
    @Query("UPDATE Notification _notification "
            + "SET _notification.isRead = :status, _notification.updatedAt = :updatedAt "
            + "WHERE _notification.id IN :notificationIds AND _notification.toMemberId = :memberId")
    void updateAllStatus(@Param("status") boolean status, @Param("memberId") String memberId,
            @Param("updatedAt") LocalDateTime updatedAt,
            @Param("notificationIds") List<NotificationId> notificationIds);

    @Modifying
    @Query("UPDATE Notification _notification "
            + "SET _notification.isDeleted = true , _notification.updatedAt = :updatedAt "
            + "WHERE _notification.toMemberId = :memberId")
    void deleteAllByMemberId(@Param("memberId") String memberId, @Param("updatedAt") LocalDateTime updatedAt);

    @Modifying
    @Query("UPDATE Notification _notification "
            + "SET _notification.isDeleted = true , _notification.updatedAt = :updatedAt "
            + "WHERE _notification.id IN :notificationIds AND _notification.toMemberId = :memberId")
    void deleteAllByIdIn(@Param("notificationIds") List<NotificationId> notificationIds,
            @Param("updatedAt") LocalDateTime updatedAt, @Param("memberId") String memberId);

    Slice<Notification> findAllByToMemberId(String toMemberId, Pageable pageable);
}
