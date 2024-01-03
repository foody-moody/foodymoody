package com.foodymoody.be.notification.infra.persistence.jpa;

import com.foodymoody.be.notification.domain.NotificationSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationSummaryJpaRepository extends JpaRepository<NotificationSummary, String>,
        JpaSpecificationExecutor<NotificationSummary> {

    Page<NotificationSummary> findAll(Specification<NotificationSummary> notificationSpecification, Pageable pageable);
}
