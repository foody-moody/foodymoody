package com.foodymoody.be.feed_comment_like_count.domain;

import com.foodymoody.be.common.util.ids.FeedCommentId;

public interface FeedCommentLikeCountRepository {

    FeedCommentLikeCount save(FeedCommentLikeCount feedCommentLikeCount);

    void incrementCount(FeedCommentId feedCommentId);

    void decrementCount(FeedCommentId feedCommentId);
}
