package com.foodymoody.be.notification.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.notification.domain.FeedNotification;
import io.lettuce.core.dynamic.annotation.Param;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NotificationJpaRepository extends
        JpaRepository<FeedNotification, NotificationId>,
        JpaSpecificationExecutor<FeedNotification> {

    @Modifying
    @Query("UPDATE FeedNotification _notification " +
            "SET _notification.isRead = :isRead, _notification.updatedAt = :updatedAt " +
            "WHERE _notification.toMemberId = :memberId")
    void updateAllReadStatus(
            @Param("isRead") boolean isRead,
            @Param("memberId") MemberId memberId,
            @Param("updatedAt") LocalDateTime updatedAt
    );

    @Modifying
    @Query("UPDATE FeedNotification _notification " +
            "SET _notification.isDeleted = true, _notification.updatedAt = :updatedAt " +
            "WHERE _notification.isRead = true AND _notification.toMemberId = :memberId")
    void deleteRead(
            @Param("memberId") MemberId memberId,
            @Param("updatedAt") LocalDateTime updatedAt
    );
}
