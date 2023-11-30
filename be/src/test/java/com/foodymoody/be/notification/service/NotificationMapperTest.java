package com.foodymoody.be.notification.service;

import com.foodymoody.be.notification.application.NotificationMapper;
import com.foodymoody.be.notification.util.NotificationFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotificationMapperTest {

    NotificationMapper notificationMapper;

    @BeforeEach
    void setUp() {
        notificationMapper = new NotificationMapper();
    }

    @DisplayName("알람 이벤트를 받아서 알람 엔티티로 변환한다.")
    @Test
    void createNotificationEntityFromEvent() {
        // given
        var commentAddNotificationEvent = NotificationFixture.commentAddNotificationEvent();
        var notificationId = NotificationFixture.notificationId();

        // when
        var notification = notificationMapper.createNotificationEntityFromEvent(notificationId,
                commentAddNotificationEvent);

        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals(notificationId, notification.getId()),
                () -> Assertions.assertEquals(commentAddNotificationEvent.getMemberId(), notification.getMemberId()),
                () -> Assertions.assertEquals(commentAddNotificationEvent.getMessage(), notification.getMessage()),
                () -> Assertions.assertEquals(commentAddNotificationEvent.getNotificationType(),
                        notification.getType()),
                () -> Assertions.assertFalse(notification.isRead()),
                () -> Assertions.assertFalse(notification.isDeleted()),
                () -> Assertions.assertEquals(commentAddNotificationEvent.getCreatedAt(), notification.getCreatedAt()),
                () -> Assertions.assertEquals(commentAddNotificationEvent.getCreatedAt(), notification.getUpdatedAt())
        );
    }

    @DisplayName("알람 엔티티 목록을 받아서 알람 응답 DTO 목록으로 변환한다.")
    @Test
    void generateResponseDtoSliceFromNotifications() {
        // given
        var notifications = NotificationFixture.notifications();

        // when
        var notificationDto = notificationMapper.generateResponseDtoSliceFromNotifications(notifications);

        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals(notifications.getContent().size(), notificationDto.getContent().size()),
                () -> Assertions.assertEquals(notifications.getContent().get(0).getId().getValue(),
                        notificationDto.getContent().get(0).getId()),
                () -> Assertions.assertEquals(notifications.getContent().get(0).getMessage(),
                        notificationDto.getContent().get(0).getMessage()),
                () -> Assertions.assertEquals(notifications.getContent().get(0).getType(),
                        notificationDto.getContent().get(0).getType()),
                () -> Assertions.assertEquals(notifications.getContent().get(0).isRead(),
                        notificationDto.getContent().get(0).isRead())
        );
    }
}
