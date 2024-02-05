package com.foodymoody.be.notification_setting.application.usecase;

import lombok.Getter;

@Getter
public class NotificationSettingResponse {

    private final boolean allNotification;
    private final boolean feedLike;
    private final boolean collectionLike;
    private final boolean commentLike;
    private final boolean follow;
    private final boolean feedComment;
    private final boolean collectionComment;

    public NotificationSettingResponse(
            boolean allNotification,
            boolean feedLike,
            boolean collectionLike,
            boolean commentLike,
            boolean follow,
            boolean feedComment,
            boolean collectionComment
    ) {
        this.allNotification = allNotification;
        this.feedLike = feedLike;
        this.collectionLike = collectionLike;
        this.commentLike = commentLike;
        this.follow = follow;
        this.feedComment = feedComment;
        this.collectionComment = collectionComment;
    }
}
