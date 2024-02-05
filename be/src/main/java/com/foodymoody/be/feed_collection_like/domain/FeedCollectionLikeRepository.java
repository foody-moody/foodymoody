package com.foodymoody.be.feed_collection_like.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionLikeId;
import com.foodymoody.be.common.util.ids.MemberId;

public interface FeedCollectionLikeRepository {

    FeedCollectionLike save(FeedCollectionLike like);

    void deleteByIdAndMemberId(FeedCollectionLikeId likeId, MemberId memberId);
}
