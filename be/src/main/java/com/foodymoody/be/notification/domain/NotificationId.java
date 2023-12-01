package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.util.BaseId;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class NotificationId extends BaseId {

    NotificationId(String value) {
        super(value);
    }
}
