package com.foodymoody.be.feed_comment_like_count.infra.event;

import com.foodymoody.be.common.util.ids.FeedCommentLikeCountId;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentAddedEvent;
import com.foodymoody.be.feed_comment_like_count.domain.FeedCommentLikeCount;

public class FeedCommentLikeCountMapper {

    public static final long INIT_COUNT = 0L;

    private FeedCommentLikeCountMapper() {
        throw new AssertionError();
    }

    public static FeedCommentLikeCount toFeedCommentLikeCount(
            FeedCommentAddedEvent event,
            FeedCommentLikeCountId feedCommentLikeCountId
    ) {
        return new FeedCommentLikeCount(
                feedCommentLikeCountId,
                event.getFeedCommentId(),
                INIT_COUNT
        );
    }

}
