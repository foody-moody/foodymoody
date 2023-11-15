package com.foodymoody.be.notification.repository;

import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<Notification, NotificationId> {

    Slice<Notification> findAllByMemberId(String memberId, Pageable pageable);

    @Modifying
    @Query("UPDATE Notification _notification SET _notification.isRead = :status, _notification.updatedAt = :updatedAt WHERE _notification.id IN :notificationIds AND _notification.memberId = :memberId")
    void updateAllStatus(boolean status, String memberId, LocalDateTime updatedAt,
            List<NotificationId> notificationIds);

    @Modifying
    @Query("UPDATE Notification _notification SET _notification.isDeleted = true , _notification.updatedAt = :updatedAt WHERE _notification.memberId = :memberId")
    void deleteAllByMemberId(String memberId, LocalDateTime updatedAt);

    @Modifying
    @Query("UPDATE Notification _notification SET _notification.isDeleted = true , _notification.updatedAt = :updatedAt WHERE _notification.id IN :notificationIds AND _notification.memberId = :memberId")
    void deleteAllByIdIn(List<NotificationId> notificationIds, LocalDateTime updatedAt, String memberId);
}
