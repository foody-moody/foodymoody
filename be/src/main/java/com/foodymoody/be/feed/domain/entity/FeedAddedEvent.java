package com.foodymoody.be.feed.domain.entity;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedAddedEvent implements NotificationEvent {

    private final MemberId fromMemberId;
    private final FeedId feedId;
    private final String profileImageUrl;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private FeedAddedEvent(
            MemberId fromMemberId,
            FeedId feedId,
            String profileImageUrl,
            NotificationType notificationType,
            LocalDateTime createdAt
    ) {
        this.fromMemberId = fromMemberId;
        this.feedId = feedId;
        this.profileImageUrl = profileImageUrl;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static FeedAddedEvent of(
            MemberId fromMemberId,
            FeedId feedId,
            String profileImageUrl,
            LocalDateTime createdAt
    ) {
        return new FeedAddedEvent(
                fromMemberId,
                feedId,
                profileImageUrl,
                NotificationType.FEED_ADDED_EVENT,
                createdAt
        );
    }

}
