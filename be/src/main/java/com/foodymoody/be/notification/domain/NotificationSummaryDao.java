package com.foodymoody.be.notification.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;

public interface NotificationSummaryDao {

    Slice<NotificationSummary> findAllByMemberId(Specification<NotificationSummary> notificationSpecification,
            Pageable pageable);
}
