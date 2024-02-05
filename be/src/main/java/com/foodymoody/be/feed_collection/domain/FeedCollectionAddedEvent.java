package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedCollectionAddedEvent implements NotificationEvent {

    private final MemberId fromMemberId;
    private final FeedCollectionId feedCollectionId;
    private final String feedCollectionTitle;
    private final String feedCollectionThumbnailUrl;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private FeedCollectionAddedEvent(
            MemberId fromMemberId,
            FeedCollectionId feedCollectionId,
            String feedCollectionTitle,
            String feedCollectionThumbnailUrl,
            NotificationType notificationType,
            LocalDateTime createdAt
    ) {
        this.fromMemberId = fromMemberId;
        this.feedCollectionId = feedCollectionId;
        this.feedCollectionTitle = feedCollectionTitle;
        this.feedCollectionThumbnailUrl = feedCollectionThumbnailUrl;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static FeedCollectionAddedEvent of(
            MemberId fromMemberId,
            FeedCollectionId feedCollectionId,
            String feedCollectionTitle,
            String feedCollectionThumbnailUrl,
            LocalDateTime createdAt
    ) {
        return new FeedCollectionAddedEvent(
                fromMemberId,
                feedCollectionId,
                feedCollectionTitle,
                feedCollectionThumbnailUrl,
                NotificationType.FEED_COLLECTION_ADDED_EVENT,
                createdAt
        );
    }
}
