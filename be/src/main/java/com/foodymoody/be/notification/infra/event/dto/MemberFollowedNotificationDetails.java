package com.foodymoody.be.notification.infra.event.dto;

import com.foodymoody.be.notification.domain.NotificationDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberFollowedNotificationDetails extends NotificationDetails {

    private boolean followed;

    public MemberFollowedNotificationDetails(boolean followed) {
        this.followed = followed;
    }

}
