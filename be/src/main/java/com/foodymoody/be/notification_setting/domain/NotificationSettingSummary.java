package com.foodymoody.be.notification_setting.domain;

public interface NotificationSettingSummary {

    boolean isFeedLike();

    boolean isCollectionLike();

    boolean isCommentLike();

    boolean isFollow();

    boolean isFeedComment();

    boolean isCollectionComment();
}
