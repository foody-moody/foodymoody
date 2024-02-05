package com.foodymoody.be.feed_reply_like_count.infra.event;

import com.foodymoody.be.common.util.ids.FeedReplyLikeCountId;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentReplyAddedEvent;
import com.foodymoody.be.feed_reply_like_count.domain.FeedReplyLikeCount;

public class FeedReplyLikeCountMapper {

    public static final long INIT_COUNT = 0L;

    private FeedReplyLikeCountMapper() {
        throw new AssertionError();
    }

    public static FeedReplyLikeCount toFeedReplyLikeCount(
            FeedCommentReplyAddedEvent event,
            FeedReplyLikeCountId feedReplyLikeCountId
    ) {
        return new FeedReplyLikeCount(feedReplyLikeCountId, event.getFeedReplyId(), INIT_COUNT);
    }
}
