package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import java.time.LocalDateTime;

public class FeedCollectionAddedEvent implements Event {

    private final FeedCollectionId feedCollectionId;
    private final LocalDateTime createdAt;

    private FeedCollectionAddedEvent(FeedCollectionId feedCollectionId, LocalDateTime createdAt) {
        this.feedCollectionId = feedCollectionId;
        this.createdAt = createdAt;
    }

    public static FeedCollectionAddedEvent of(FeedCollectionId feedCollectionId, LocalDateTime createdAt) {
        return new FeedCollectionAddedEvent(feedCollectionId, createdAt);
    }

    public FeedCollectionId getFeedCollectionId() {
        return feedCollectionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
