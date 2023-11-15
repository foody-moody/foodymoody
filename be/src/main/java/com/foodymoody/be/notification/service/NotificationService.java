package com.foodymoody.be.notification.service;

import com.foodymoody.be.comment.domain.CommentAddNotificationEvent;
import com.foodymoody.be.member.service.MemberService;
import com.foodymoody.be.notification.controller.dto.NotificationResponse;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import com.foodymoody.be.notification.repository.NotificationRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final MemberService memberService;
    private final NotificationMapper notificationMapper;

    @EventListener(CommentAddNotificationEvent.class)
    @Transactional
    public void saveNotification(CommentAddNotificationEvent event) {
        NotificationId notificationId = NotificationId.newId();
        Notification notification = notificationMapper.createNotificationEntityFromEvent(notificationId, event);
        notificationRepository.save(notification);
    }

    @Transactional
    public void changeStatus(String memberId, String notificationId, boolean isRead) {
        Notification notification = getNotification(notificationId);
        var updatedAt = LocalDateTime.now();
        notification.changeStatus(isRead, memberId, updatedAt);
    }

    @Transactional(readOnly = true)
    public Slice<NotificationResponse> requestAll(String memberId, Pageable pageable) {
        memberService.findById(memberId);
        Slice<Notification> notifications = notificationRepository.findAllByMemberId(memberId, pageable);
        return notificationMapper.generateResponseDtoSliceFromNotifications(notifications);
    }

    @Transactional
    public void delete(String memberId, String notificationId) {
        Notification notification = getNotification(notificationId);
        LocalDateTime updatedAt = LocalDateTime.now();
        notification.delete(memberId, updatedAt);
    }

    @Transactional
    public void deleteAll(String memberId) {
        memberService.findById(memberId);
        notificationRepository.deleteAllByMemberId(memberId, LocalDateTime.now());
    }

    @Transactional
    public NotificationResponse requestOne(String memberId, String notificationId) {
        memberService.findById(memberId);
        Notification notification = getNotification(notificationId);
        notification.changeStatus(true, memberId, LocalDateTime.now());
        return notificationMapper.generateResponseDtoFromNotification(notification);
    }

    @Transactional
    public void changeAllStatus(String memberId, List<String> notificationIds, boolean read) {
        memberService.findById(memberId);
        notificationRepository.updateAllStatus(read, memberId, LocalDateTime.now(),
                NotificationMapper.toNotificationID(notificationIds));
    }

    public Notification getNotification(String notificationId) {
        return notificationRepository.findById(NotificationId.from(notificationId)).orElseThrow();
    }

    @Transactional
    public void deleteAll(String memberId, List<String> notificationIds) {
        memberService.findById(memberId);
        notificationRepository.deleteAllByIdIn(
                NotificationMapper.toNotificationID(notificationIds),
                LocalDateTime.now(),
                memberId
        );
    }
}
