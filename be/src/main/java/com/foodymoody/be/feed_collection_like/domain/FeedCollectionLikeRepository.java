package com.foodymoody.be.feed_collection_like.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.util.Optional;

public interface FeedCollectionLikeRepository {

    FeedCollectionLike save(FeedCollectionLike like);

    void deleteByIdAndMemberId(FeedCollectionLikeId likeId, MemberId memberId);

    Optional<FeedCollectionLike> findByFeedCollectionIdAndMemberId(
            FeedCollectionId feedCollectionId, MemberId memberId
    );

    boolean existsByFeedCollectionIdAndMemberId(FeedCollectionId feedCollectionId, MemberId memberId);
}
