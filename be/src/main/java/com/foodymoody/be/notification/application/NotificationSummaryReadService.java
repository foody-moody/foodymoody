package com.foodymoody.be.notification.application;


import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification.domain.NotificationSummary;
import com.foodymoody.be.notification.domain.NotificationSummaryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationSummaryReadService {

    private final NotificationSummaryDao dao;

    public Slice<NotificationSummary> requestAll(MemberId memberId, Specification<NotificationSummary> spec,
            Pageable pageable) {
        spec = spec.and(NotificationSummarySpecs.isToMemberSpec(memberId));
        return dao.findAllByMemberId(spec, pageable);
    }
}
