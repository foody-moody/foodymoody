package com.foodymoody.be.notification.infra.event.dto;

import com.foodymoody.be.notification.domain.NotificationDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberFollowedNotificationDetails extends NotificationDetails {

    private boolean isFollowed;

    public MemberFollowedNotificationDetails(boolean isFollowed) {
        this.isFollowed = isFollowed;
    }
}
