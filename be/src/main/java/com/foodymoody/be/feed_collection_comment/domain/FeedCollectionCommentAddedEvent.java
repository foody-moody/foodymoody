package com.foodymoody.be.feed_collection_comment.domain;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import java.time.LocalDateTime;

public class FeedCollectionCommentAddedEvent implements Event {

    private final FeedCollectionCommentId feedCollectionCommentId;
    private final LocalDateTime createdAt;

    private FeedCollectionCommentAddedEvent(FeedCollectionCommentId feedCollectionCommentId, LocalDateTime createdAt) {
        this.feedCollectionCommentId = feedCollectionCommentId;
        this.createdAt = createdAt;
    }

    public static FeedCollectionCommentAddedEvent of(
            FeedCollectionCommentId feedCollectionCommentId, LocalDateTime createdAt
    ) {
        return new FeedCollectionCommentAddedEvent(feedCollectionCommentId, createdAt);
    }

    public FeedCollectionCommentId getFeedCollectionCommentId() {
        return feedCollectionCommentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
