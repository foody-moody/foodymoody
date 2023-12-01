package com.foodymoody.be.notification.application;

import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import com.foodymoody.be.notification.infra.NotificationRepository;
import com.foodymoody.be.notification.presentation.dto.NotificationResponse;
import java.time.LocalDateTime;
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
    private final NotificationMapper notificationMapper;

    public Slice<NotificationResponse> requestAll(String memberId, Pageable pageable) {
        Slice<Notification> notifications = notificationRepository.findAllByMemberId(memberId, pageable);
        return notificationMapper.generateResponseDtoSliceFromNotifications(notifications);
    }

    public NotificationResponse requestOne(String memberId, String notificationId) {
        Notification notification = getNotification(notificationId);
        notification.changeStatus(true, memberId, LocalDateTime.now());
        return notificationMapper.generateResponseDtoFromNotification(notification);
    }


    public long countByMemberId(String memberId) {
        return notificationRepository.countByMemberIdAndIsRead(memberId, false);
    }

    public Notification getNotification(String notificationId) {
        return notificationRepository.findById(NotificationIdFactory.from(notificationId)).orElseThrow();
    }
}
