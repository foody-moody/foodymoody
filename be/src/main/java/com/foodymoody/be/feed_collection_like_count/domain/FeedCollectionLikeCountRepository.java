package com.foodymoody.be.feed_collection_like_count.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import java.util.Optional;

public interface FeedCollectionLikeCountRepository {

    void increaseCount(FeedCollectionId feedCollectionId);

    void save(FeedCollectionLikeCount likeCount);

    void decreaseCount(FeedCollectionId feedCollectionId);

    Optional<FeedCollectionLikeCount> findByFeedCollectionId(FeedCollectionId feedCollectionId);
}
