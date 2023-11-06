package com.foodymoody.be.notification.repository;

import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, NotificationId> {

    Slice<Notification> findAllByMemberId(String memberId, Pageable pageable);

    void deleteAllByMemberId(String memberId);
}
