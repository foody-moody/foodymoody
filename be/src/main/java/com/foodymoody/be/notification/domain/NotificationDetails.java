package com.foodymoody.be.notification.domain;

import java.util.Map;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class NotificationDetails {

    @Getter
    @Convert(converter = NotificationDetailsConverter.class)
    private Map<String, Object> data;

    public NotificationDetails(Map<String, Object> data) {
        this.data = data;
    }
}
