package com.foodymoody.be.notification.infra.persistence;

import com.foodymoody.be.notification.domain.NotificationSummary;
import com.foodymoody.be.notification.domain.NotificationSummaryDao;
import com.foodymoody.be.notification.infra.persistence.jpa.NotificationSummaryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class NotificationSummaryDaoImpl implements NotificationSummaryDao {

    private final NotificationSummaryJpaRepository notificationSummaryJpaRepository;

    @Override
    public Slice<NotificationSummary> findAllByMemberId(Specification<NotificationSummary> notificationSpecification,
            Pageable pageable) {
        return notificationSummaryJpaRepository.findAll(notificationSpecification, pageable);
    }
}
