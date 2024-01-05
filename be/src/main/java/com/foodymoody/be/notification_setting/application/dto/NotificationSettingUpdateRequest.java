package com.foodymoody.be.notification_setting.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NotificationSettingUpdateRequest {

    private boolean isFeedLike;
    private boolean isCollectionLike;
    private boolean isCommentLike;
    private boolean isFollow;
    private boolean isFeedComment;
    private boolean isCollectionComment;
}
