package com.foodymoody.be.notification_setting.infra.usecase;

import com.foodymoody.be.common.util.ids.NotificationSettingId;
import lombok.Getter;

@Getter
public class NotificationSettingResponse {

    private NotificationSettingId id;
    private boolean allNotification;
    private boolean feedLike;
    private boolean collectionLike;
    private boolean commentLike;
    private boolean follow;
    private boolean feedComment;
    private boolean collectionComment;

    public NotificationSettingResponse(
            NotificationSettingId id, boolean allNotification, boolean feedLike, boolean collectionLike,
            boolean commentLike, boolean follow, boolean feedComment, boolean collectionComment
    ) {
        this.id = id;
        this.allNotification = allNotification;
        this.feedLike = feedLike;
        this.collectionLike = collectionLike;
        this.commentLike = commentLike;
        this.follow = follow;
        this.feedComment = feedComment;
        this.collectionComment = collectionComment;
    }
}
