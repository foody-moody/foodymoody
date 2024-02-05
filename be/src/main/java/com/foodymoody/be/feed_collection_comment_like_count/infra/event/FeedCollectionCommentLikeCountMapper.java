package com.foodymoody.be.feed_collection_comment_like_count.infra.event;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentLikeCountId;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentAddedEvent;
import com.foodymoody.be.feed_collection_comment_like_count.domain.FeedCollectionCommentLikeCount;

public class FeedCollectionCommentLikeCountMapper {

    public static final long INIT_COUNT = 0L;

    private FeedCollectionCommentLikeCountMapper() {
        throw new AssertionError();
    }

    public static FeedCollectionCommentLikeCount toLikeCount(
            FeedCollectionCommentAddedEvent event,
            FeedCollectionCommentLikeCountId id
    ) {
        return new FeedCollectionCommentLikeCount(
                id,
                event.getFeedCollectionCommentId(),
                INIT_COUNT
        );
    }
}
