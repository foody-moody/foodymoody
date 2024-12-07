package com.foodymoody.be.feed_collection_comment_like_count.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;

public interface FeedCollectionCommentLikeCountRepository {

    void increase(FeedCollectionCommentId feedCollectionCommentId);

    void decrease(FeedCollectionCommentId feedCollectionCommentId);

    FeedCollectionCommentLikeCount save(FeedCollectionCommentLikeCount likeCount);

}
