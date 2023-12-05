package com.foodymoody.be.notification.application;

import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import com.foodymoody.be.notification.infra.persistence.jpa.NotificationJpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationWriteService {

    private final NotificationJpaRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Transactional
    public void save(FeedNotification feedNotification) {
        notificationRepository.save(feedNotification);
    }

    @Transactional
    public void changeStatus(String memberId, String notificationId, boolean isRead) {
        FeedNotification feedNotification = this.getNotification(notificationId);
        var updatedAt = LocalDateTime.now();
        feedNotification.changeStatus(isRead, memberId, updatedAt);
    }

    @Transactional
    public void delete(String memberId, String notificationId) {
        FeedNotification feedNotification = this.getNotification(notificationId);
        LocalDateTime updatedAt = LocalDateTime.now();
        feedNotification.delete(memberId, updatedAt);
    }

    @Transactional
    public void deleteAll(String memberId) {
        notificationRepository.deleteAllByMemberId(memberId, LocalDateTime.now());
    }

    @Transactional
    public void changeAllStatus(String memberId, List<String> notificationIds, boolean read) {
        notificationRepository.updateAllStatus(read, memberId, LocalDateTime.now(),
                notificationMapper.toNotificationID(notificationIds));
    }

    @Transactional
    public void deleteAll(String memberId, List<String> notificationIds) {
        notificationRepository.deleteAllByIdIn(notificationMapper.toNotificationID(notificationIds),
                LocalDateTime.now(), memberId);
    }

    public FeedNotification getNotification(String notificationId) {
        return notificationRepository.findById(NotificationIdFactory.from(notificationId)).orElseThrow();
    }

    @Transactional
    public FeedNotification read(String notificationId) {
        FeedNotification feedNotification = this.getNotification(notificationId);
        feedNotification.changeStatus(true, feedNotification.getToMemberId(), LocalDateTime.now());
        return feedNotification;
    }
}
