package com.foodymoody.be.notification_setting.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NotificationSettingUpdateRequest {

    private boolean isComment;
    private boolean isHeart;
    private boolean isFeed;
}
