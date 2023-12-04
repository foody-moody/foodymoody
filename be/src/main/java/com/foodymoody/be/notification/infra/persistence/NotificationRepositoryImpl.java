package com.foodymoody.be.notification.infra.persistence;

import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationRepository;
import com.foodymoody.be.notification.infra.persistence.jpa.NotificationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationJpaRepository notificationJpaRepository;

    @Override
    public long count(Specification<Notification> specification) {
        return notificationJpaRepository.count(specification);
    }

    @Override
    public Slice<Notification> findAll(Specification<Notification> notificationSpecification, Pageable pageable) {
        return notificationJpaRepository.findAll(notificationSpecification, pageable);
    }
}
