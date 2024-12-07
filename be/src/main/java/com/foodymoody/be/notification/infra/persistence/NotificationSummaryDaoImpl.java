package com.foodymoody.be.notification.infra.persistence;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification.domain.NotificationSummary;
import com.foodymoody.be.notification.domain.NotificationSummaryDao;
import com.foodymoody.be.notification.infra.persistence.jpa.NotificationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class NotificationSummaryDaoImpl implements NotificationSummaryDao {

    private final NotificationJpaRepository notificationJpaRepository;

    /**
     * Retrieves all the notification summaries for a given member ID.
     *
     * @param memberId the ID of the member
     * @param pageable the pagination information
     * @return a {@link Slice} of {@link NotificationSummary} objects
     */
    @Override
    public Slice<NotificationSummary> findAllByMemberId(MemberId memberId, Pageable pageable) {
        return notificationJpaRepository.findAllSummaryByMemberId(memberId, pageable);
    }

}
