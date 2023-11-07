package com.foodymoody.be.notification.service;

import com.foodymoody.be.comment.domain.CommentAddNotificationEvent;
import com.foodymoody.be.member.service.MemberService;
import com.foodymoody.be.notification.controller.dto.NotificationResponse;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import com.foodymoody.be.notification.repository.NotificationRepository;
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

    public static void hasAuthentication(Notification notification, String memberId, String message) {
        if (!notification.isSameMember(memberId)) {
            throw new IllegalArgumentException(message);
        }
    }

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
        notification.changeStatus(isRead, memberId);
    }

    @Transactional(readOnly = true)
    public Slice<NotificationResponse> request(String memberId, Pageable pageable) {
        memberService.findById(memberId);
        Slice<Notification> notifications = notificationRepository.findAllByMemberId(memberId, pageable);
        return notificationMapper.generateResponseDtoSliceFromNotifications(notifications);
    }

    @Transactional
    public void delete(String memberId, String notificationId) {
        Notification notification = getNotification(notificationId);
        notification.delete(memberId);
    }

    @Transactional
    public void deleteAll(String memberId) {
        memberService.findById(memberId);
        notificationRepository.deleteAllByMemberId(memberId);
    }

    private Notification getNotification(String notificationId) {
        return notificationRepository.findById(NotificationId.from(notificationId))
                .orElseThrow();
    }

}
