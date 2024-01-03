package com.foodymoody.be.notification_setting.domain;

import com.foodymoody.be.common.util.ids.NotificationSettingId;

public interface NotificationSettingSummary {

    NotificationSettingId getId();

    boolean isFeedLike();

    boolean isCollectionLike();

    boolean isCommentLike();

    boolean isFollow();

    boolean isFeedComment();

    boolean isCollectionComment();
}
