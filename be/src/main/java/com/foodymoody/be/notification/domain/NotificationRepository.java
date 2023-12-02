package com.foodymoody.be.notification.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;

public interface NotificationRepository {

    long count(Specification<Notification> specification);

    Slice<Notification> findAll(Specification<Notification> notificationSpecification, Pageable pageable);
}
