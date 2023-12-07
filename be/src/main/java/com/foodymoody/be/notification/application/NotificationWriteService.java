package com.foodymoody.be.notification.application;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
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
        FeedNotification feedNotification = getNotification(notificationId);
        MemberId memberIdObj = IdFactory.createMemberId(memberId);
        var updatedAt = LocalDateTime.now();
        feedNotification.changeStatus(isRead, memberIdObj, updatedAt);
    }

    @Transactional
    public void delete(String memberId, String notificationId) {
        FeedNotification feedNotification = this.getNotification(notificationId);
        MemberId memberIdObj = IdFactory.createMemberId(memberId);
        LocalDateTime updatedAt = LocalDateTime.now();
        feedNotification.delete(memberIdObj, updatedAt);
    }

    @Transactional
    public void deleteAll(String memberIdValue) {
        MemberId memberId = IdFactory.createMemberId(memberIdValue);
        notificationRepository.deleteAllByMemberId(memberId, LocalDateTime.now());
    }

    @Transactional
    public void changeAllStatus(String memberIdValue, List<String> notificationIds, boolean read) {
        MemberId memberId = IdFactory.createMemberId(memberIdValue);
        notificationRepository.updateAllStatus(read, memberId, LocalDateTime.now(),
                notificationMapper.toNotificationID(notificationIds));
    }

    @Transactional
    public void deleteAll(String memberIdValue, List<String> notificationIds) {
        MemberId memberId = IdFactory.createMemberId(memberIdValue);
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
