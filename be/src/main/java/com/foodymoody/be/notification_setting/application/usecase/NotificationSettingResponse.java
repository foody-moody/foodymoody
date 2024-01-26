package com.foodymoody.be.notification_setting.application.usecase;

import lombok.Getter;

@Getter
public class NotificationSettingResponse {

    private boolean allNotification;
    private boolean feedLike;
    private boolean collectionLike;
    private boolean commentLike;
    private boolean follow;
    private boolean feedComment;
    private boolean collectionComment;

    public NotificationSettingResponse(
            boolean allNotification, boolean feedLike, boolean collectionLike,
            boolean commentLike, boolean follow, boolean feedComment, boolean collectionComment
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
