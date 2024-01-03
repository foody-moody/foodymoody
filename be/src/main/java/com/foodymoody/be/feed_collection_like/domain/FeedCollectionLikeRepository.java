package com.foodymoody.be.feed_collection_like.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.util.Optional;

public interface FeedCollectionLikeRepository {

    FeedCollectionLike save(FeedCollectionLike like);

    Optional<FeedCollectionLike> findById(FeedCollectionLikeId likeId);

    void deleteByIdAndMemberId(FeedCollectionLikeId likeId, MemberId memberId);
}
