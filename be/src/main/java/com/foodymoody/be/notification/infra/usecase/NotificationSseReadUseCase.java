package com.foodymoody.be.notification.infra.usecase;

import com.foodymoody.be.notification.application.NotificationReadService;
import com.foodymoody.be.notification.application.NotificationSpecs;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import com.foodymoody.be.notification_setting.domain.NotificationSettingSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationSseReadUseCase {

    private final NotificationReadService notificationReadService;
    private final NotificationSettingReadService notificationSettingReadService;

    @Transactional(readOnly = true)
    public long fetchCountNotReadNotification(String memberId) {
        NotificationSettingSummary notificationSettingSummary = notificationSettingReadService.request(memberId);
        Specification<Notification> notificationSpecification = NotificationSpecs.searchByType(
                notificationSettingSummary.isComment(), notificationSettingSummary.isHeart(),
                notificationSettingSummary.isFeed());
        return notificationReadService.fetchCountNotReadNotification(memberId, notificationSpecification);
    }
}
