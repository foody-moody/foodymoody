package com.foodymoody.be.notification.application;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.infra.persistence.jpa.NotificationJpaRepository;
import java.time.LocalDateTime;
import java.util.List;
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
    public void changeStatus(MemberId memberId, NotificationId notificationId, boolean isRead) {
        FeedNotification feedNotification = getNotification(notificationId);
        var updatedAt = LocalDateTime.now();
        feedNotification.changeStatus(isRead, memberId, updatedAt);
    }

    @Transactional
    public void delete(MemberId memberId, NotificationId notificationId) {
        FeedNotification feedNotification = this.getNotification(notificationId);
        LocalDateTime updatedAt = LocalDateTime.now();
        feedNotification.delete(memberId, updatedAt);
    }

    @Transactional
    public void deleteAll(MemberId memberId) {
        notificationRepository.deleteAllByMemberId(memberId, LocalDateTime.now());
    }

    @Transactional
    public void changeAllStatus(MemberId memberId, List<NotificationId> notificationIds, boolean read) {
        notificationRepository.updateAllStatus(read, memberId, LocalDateTime.now(), notificationIds);
    }

    @Transactional
    public void deleteAll(MemberId memberId, List<NotificationId> notificationIds) {
        LocalDateTime now = LocalDateTime.now();
        notificationRepository.deleteAllByIdIn(notificationIds, now, memberId
        );
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
}
