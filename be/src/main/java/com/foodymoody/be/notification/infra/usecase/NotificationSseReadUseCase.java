package com.foodymoody.be.notification.infra.usecase;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.notification.application.NotificationReadService;
import com.foodymoody.be.notification.application.NotificationSpecs;
import com.foodymoody.be.notification.domain.FeedNotification;
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
    public long fetchCountNotReadNotification(String memberIdValue) {
        var memberId = IdFactory.createMemberId(memberIdValue);
        var notificationSettingSummary = notificationSettingReadService.request(memberId);
        var notificationSpecification = getNotificationSpecification(notificationSettingSummary);
        return notificationReadService.fetchCountNotReadNotification(memberId, notificationSpecification);
    }

    private static Specification<FeedNotification> getNotificationSpecification(
            NotificationSettingSummary notificationSettingSummary
    ) {
        return NotificationSpecs.searchByType(
                notificationSettingSummary.isFeedComment(), notificationSettingSummary.isCollectionComment(),
                notificationSettingSummary.isFeedLike(), notificationSettingSummary.isCollectionLike(),
                notificationSettingSummary.isCommentLike(), notificationSettingSummary.isFollow()
        );
    }
}
