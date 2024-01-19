package com.foodymoody.be.feed_collection_like.domain;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedCollectionLikeAddedEvent implements NotificationEvent {

    private final MemberId fromMemberId;
    private final FeedCollectionId feedCollectionId;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private FeedCollectionLikeAddedEvent(
            MemberId fromMemberId,
            FeedCollectionId feedCollectionId,
            NotificationType notificationType,
            LocalDateTime createdAt
    ) {
        this.fromMemberId = fromMemberId;
        this.feedCollectionId = feedCollectionId;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static FeedCollectionLikeAddedEvent of(
            MemberId fromMemberId,
            FeedCollectionId feedCollectionId,
            LocalDateTime createdAt
    ) {
        return new FeedCollectionLikeAddedEvent(
                fromMemberId,
                feedCollectionId,
                NotificationType.FEED_COLLECTION_LIKED_ADDED_EVENT,
                createdAt
        );
    }
}
