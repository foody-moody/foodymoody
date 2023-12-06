package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.util.BaseId;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class FeedNotificationId extends BaseId {

    FeedNotificationId(String value) {
        super(value);
    }
}
