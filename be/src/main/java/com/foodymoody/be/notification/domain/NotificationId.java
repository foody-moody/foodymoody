package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.util.IdGenerator;
import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class NotificationId implements Serializable {

    private static final long serialVersionUID = -5351955236373496259L;

    private String value;

    private NotificationId(String value) {
        this.value = value;
    }

    public static NotificationId from(String id) {
        return new NotificationId(id);
    }

    public static NotificationId newId() {
        return new NotificationId(IdGenerator.generate());
    }

    public String getValue() {
        return value;
    }
}
