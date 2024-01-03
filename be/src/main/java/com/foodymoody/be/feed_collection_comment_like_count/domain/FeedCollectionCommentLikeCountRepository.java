package com.foodymoody.be.feed_collection_comment_like_count.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import java.util.Optional;

public interface FeedCollectionCommentLikeCountRepository {

    Optional<FeedCollectionCommentLikeCount> findByFeedCollectionCommentId(
            FeedCollectionCommentId feedCollectionCommentId
    );

    void increaseCount(FeedCollectionCommentId feedCollectionCommentId);

    void decreaseCount(FeedCollectionCommentId feedCollectionCommentId);

    FeedCollectionCommentLikeCount save(FeedCollectionCommentLikeCount likeCount);
}
