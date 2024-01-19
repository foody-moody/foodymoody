package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MemberFollowedEvent implements NotificationEvent {

    private final MemberId fromMemberId;
    private final MemberId toMemberId;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private MemberFollowedEvent(
            MemberId fromMemberId,
            MemberId toMemberId,
            NotificationType notificationType,
            LocalDateTime createdAt
    ) {
        this.fromMemberId = fromMemberId;
        this.toMemberId = toMemberId;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static MemberFollowedEvent of(
            MemberId fromMemberId,
            MemberId toMemberId,
            LocalDateTime createdAt
    ) {
        return new MemberFollowedEvent(
                fromMemberId,
                toMemberId,
                NotificationType.MEMBER_FOLLOWED_EVENT,
                createdAt
        );
    }
}
