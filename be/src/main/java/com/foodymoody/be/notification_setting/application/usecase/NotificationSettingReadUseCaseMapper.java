package com.foodymoody.be.notification_setting.application.usecase;

import com.foodymoody.be.notification_setting.domain.NotificationSettingSummary;

public class NotificationSettingReadUseCaseMapper {

    private NotificationSettingReadUseCaseMapper() {
        throw new AssertionError();
    }

    public static NotificationSettingResponse toResponse(NotificationSettingSummary summary) {
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

    public static boolean isAllNotification(NotificationSettingSummary summary) {
        return summary.isFeedLike() &&
                summary.isCollectionLike() &&
                summary.isCommentLike() &&
                summary.isFollow() &&
                summary.isFeedComment() &&
                summary.isCollectionComment();
    }

}
