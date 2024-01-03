package com.foodymoody.be.feed_collection_reply.domain;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;

public class FeedCollectionReplyAddedEvent implements Event {

    private final FeedCollectionReplyId feedCollectionReplyId;

    private FeedCollectionReplyAddedEvent(FeedCollectionReplyId feedCollectionReplyId) {
        this.feedCollectionReplyId = feedCollectionReplyId;
    }

    public static FeedCollectionReplyAddedEvent of(FeedCollectionReplyId feedCollectionReplyId) {
        return new FeedCollectionReplyAddedEvent(feedCollectionReplyId);
    }

    public FeedCollectionReplyId getFeedCollectionReplyId() {
        return feedCollectionReplyId;
    }
}
