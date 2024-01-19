package com.foodymoody.be.feed_like.domain;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_like.domain.entity.FeedLike;

public interface FeedLikeRepository {

    FeedLike save(FeedLike feedHeart);

    boolean existsByMemberIdAndFeedId(MemberId memberId, FeedId feedId);

    void deleteByFeedIdAndMemberId(FeedId feedId, MemberId memberId);

}
