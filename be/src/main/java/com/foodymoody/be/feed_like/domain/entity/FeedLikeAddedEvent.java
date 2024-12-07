package com.foodymoody.be.feed_like.domain.entity;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedLikeAddedEvent implements NotificationEvent {

    private final FeedId feedId;
    private final MemberId fromMemberId;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private FeedLikeAddedEvent(
            FeedId feedId,
            MemberId fromMemberId,
            NotificationType notificationType,
            LocalDateTime createdAt
    ) {
        this.feedId = feedId;
        this.fromMemberId = fromMemberId;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static FeedLikeAddedEvent of(
            FeedId feedId,
            MemberId fromMemberId,
            LocalDateTime createdAt
    ) {
        return new FeedLikeAddedEvent(
                feedId,
                fromMemberId,
                NotificationType.FEED_LIKED_ADDED_EVENT,
                createdAt
        );
    }

}
