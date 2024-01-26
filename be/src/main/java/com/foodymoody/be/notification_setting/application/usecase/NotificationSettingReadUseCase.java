package com.foodymoody.be.notification_setting.application.usecase;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification_setting.application.service.NotificationSettingReadService;
import com.foodymoody.be.notification_setting.domain.NotificationSettingSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationSettingReadUseCase {

    private final NotificationSettingReadService settingService;

    @Transactional(readOnly = true)
    public NotificationSettingResponse request(MemberId memberId) {
        var summary = settingService.request(memberId);
        return toResponse(summary);
    }

    private static NotificationSettingResponse toResponse(NotificationSettingSummary summary) {
        return new NotificationSettingResponse(
                isAllNotification(summary),
                summary.isFeedLike(),
                summary.isCollectionLike(),
                summary.isCommentLike(),
                summary.isFollow(),
                summary.isFeedComment(),
                summary.isCollectionComment()
        );
    }

    private static boolean isAllNotification(NotificationSettingSummary summary) {
        return summary.isFeedLike() && summary.isCollectionLike() && summary.isCommentLike() && summary.isFollow() &&
                summary.isFeedComment() && summary.isCollectionComment();
    }
}
