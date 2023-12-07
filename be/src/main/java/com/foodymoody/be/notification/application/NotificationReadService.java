package com.foodymoody.be.notification.application;

import static com.foodymoody.be.notification.application.NotificationSpecs.isDeletedSpec;
import static com.foodymoody.be.notification.application.NotificationSpecs.isReadSpec;
import static com.foodymoody.be.notification.application.NotificationSpecs.isToMemberSpec;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.domain.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NotificationReadService {

    private final NotificationRepository notificationRepository;

    public long fetchCountNotReadNotification(MemberId memberId, Specification<FeedNotification> specification) {
        specification = specification
                .and(isReadSpec(false))
                .and(isDeletedSpec(false))
                .and(isToMemberSpec(memberId));
        return notificationRepository.count(specification);
    }
}
