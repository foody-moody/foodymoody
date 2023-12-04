package com.foodymoody.be.notification_setting.domain;

import com.foodymoody.be.common.util.BaseId;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class NotificationSettingId extends BaseId {

    NotificationSettingId(String value) {
        super(value);
    }
}
