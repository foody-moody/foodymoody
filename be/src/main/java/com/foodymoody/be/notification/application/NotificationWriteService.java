package com.foodymoody.be.notification.application;

import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import com.foodymoody.be.notification.infra.NotificationRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationWriteService {

    private final NotificationRepository notificationRepository;

    @Transactional
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    @Transactional
    public void changeStatus(String memberId, String notificationId, boolean isRead) {
        Notification notification = getNotification(notificationId);
        var updatedAt = LocalDateTime.now();
        notification.changeStatus(isRead, memberId, updatedAt);
    }

    @Transactional
    public void delete(String memberId, String notificationId) {
        Notification notification = getNotification(notificationId);
        LocalDateTime updatedAt = LocalDateTime.now();
        notification.delete(memberId, updatedAt);
    }

    @Transactional
    public void deleteAll(String memberId) {
        notificationRepository.deleteAllByMemberId(memberId, LocalDateTime.now());
    }

    @Transactional
    public void changeAllStatus(String memberId, List<String> notificationIds, boolean read) {
        notificationRepository.updateAllStatus(read, memberId, LocalDateTime.now(),
                NotificationMapper.toNotificationID(notificationIds));
    }

    @Transactional
    public void deleteAll(String memberId, List<String> notificationIds) {
        notificationRepository.deleteAllByIdIn(NotificationMapper.toNotificationID(notificationIds),
                LocalDateTime.now(), memberId);
    }

    public Notification getNotification(String notificationId) {
        return notificationRepository.findById(NotificationIdFactory.from(notificationId)).orElseThrow();
    }
}
