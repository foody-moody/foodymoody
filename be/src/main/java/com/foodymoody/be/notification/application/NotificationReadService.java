package com.foodymoody.be.notification.application;

import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.infra.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NotificationReadService {

    private final NotificationRepository notificationRepository;

    public long countByMemberId(String memberId) {
        return notificationRepository.countByToMemberId(memberId);
    }

    public Slice<Notification> requestAll(String memberId, Pageable pageable) {
        return notificationRepository.findAllByToMemberId(memberId, pageable);
    }
}
