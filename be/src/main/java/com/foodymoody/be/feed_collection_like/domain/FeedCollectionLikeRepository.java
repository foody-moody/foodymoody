package com.foodymoody.be.feed_collection_like.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.util.Optional;

public interface FeedCollectionLikeRepository {

    FeedCollectionLike save(FeedCollectionLike like);

    Optional<FeedCollectionLike> findByFeedCollectionIdAndMemberId(
            FeedCollectionId feedCollectionId, MemberId memberId
    );

    boolean existsByFeedCollectionIdAndMemberId(FeedCollectionId feedCollectionId, MemberId memberId);

    void deleteByFeedCollectionIdAndMemberId(FeedCollectionId feedCollectionId, MemberId memberId);
}
