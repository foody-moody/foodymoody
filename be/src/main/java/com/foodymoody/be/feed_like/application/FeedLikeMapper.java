package com.foodymoody.be.feed_like.application;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_like.application.dto.response.FeedHeartResponse;
import com.foodymoody.be.feed_like.domain.entity.FeedLike;

public class FeedLikeMapper {

    private FeedLikeMapper() {
        throw new IllegalArgumentException("Utility Class");
    }

    public static FeedLike makeFeedHeartWithFeedIdAndMemberId(
            FeedLikeId feedLikeId, FeedId feedId,
            MemberId memberId, boolean isLiked
    ) {
        return new FeedLike(feedLikeId, feedId, memberId, isLiked);
    }

    public static FeedHeartResponse toHeartResponse(
            String id, String feedId, String memberId, boolean isLiked,
            int feedHeartCount
    ) {
        return new FeedHeartResponse(id, feedId, memberId, isLiked, feedHeartCount);
    }

}
