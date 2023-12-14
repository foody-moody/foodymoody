package com.foodymoody.be.notification.application;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.infra.persistence.jpa.NotificationJpaRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedNotificationWriteService {

    private final NotificationJpaRepository notificationRepository;

    @Transactional
    public void save(FeedNotification feedNotification) {
        notificationRepository.save(feedNotification);
    }

    @Transactional
    public void delete(MemberId memberId, NotificationId notificationId) {
        FeedNotification feedNotification = this.getNotification(notificationId);
        LocalDateTime updatedAt = LocalDateTime.now();
        feedNotification.delete(memberId, updatedAt);
    }

    public FeedNotification getNotification(NotificationId notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 알림이 존재하지 않습니다."));
    }

    @Transactional
    public FeedNotification read(NotificationId notificationId) {
        FeedNotification feedNotification = this.getNotification(notificationId);
        feedNotification.changeStatus(true, feedNotification.getToMemberId(), LocalDateTime.now());
        return feedNotification;
    }

    @Transactional
    public void markAllAsRead(MemberId memberId) {
        notificationRepository.updateAllReadStatus(true, memberId, LocalDateTime.now());
    }

    @Transactional
    public void deleteRead(MemberId memberId) {
        notificationRepository.deleteRead(memberId, LocalDateTime.now());
    }

    @Transactional
    public void markAsRead(MemberId memberId, NotificationId notificationID) {
        FeedNotification feedNotification = this.getNotification(notificationID);
        feedNotification.changeStatus(true, memberId, LocalDateTime.now());
    }
}
