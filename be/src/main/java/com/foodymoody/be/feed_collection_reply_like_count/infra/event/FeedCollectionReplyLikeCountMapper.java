package com.foodymoody.be.feed_collection_reply_like_count.infra.event;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyLikeCountId;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplyAddedEvent;
import com.foodymoody.be.feed_collection_reply_like_count.domain.FeedCollectionReplyLikeCount;

public class FeedCollectionReplyLikeCountMapper {

    public static final long INIT_COUNT = 0L;

    private FeedCollectionReplyLikeCountMapper() {
        throw new AssertionError();
    }

    public static FeedCollectionReplyLikeCount toLikeCount(
            FeedCollectionReplyAddedEvent event,
            FeedCollectionReplyLikeCountId id
    ) {
        return new FeedCollectionReplyLikeCount(
                id,
                event.getFeedCollectionReplyId(),
                INIT_COUNT,
                event.getCreatedAt()
        );
    }

}
