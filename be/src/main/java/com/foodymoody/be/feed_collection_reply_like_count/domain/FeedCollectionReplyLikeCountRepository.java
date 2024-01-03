package com.foodymoody.be.feed_collection_reply_like_count.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import java.util.Optional;

public interface FeedCollectionReplyLikeCountRepository {

    void increaseCount(FeedCollectionReplyId feedCollectionReplyId);

    void decreaseCount(FeedCollectionReplyId feedCollectionReplyId);

    FeedCollectionReplyLikeCount save(FeedCollectionReplyLikeCount likeCount);

    Optional<FeedCollectionReplyLikeCount> findByFeedCollectionReplyId(FeedCollectionReplyId feedCollectionReplyId);
}
