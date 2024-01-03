package com.foodymoody.be.feed_heart.application;

import com.foodymoody.be.common.util.ids.FeedHeartId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_heart.application.dto.response.FeedHeartResponse;
import com.foodymoody.be.feed_heart.domain.entity.FeedHeart;

public class FeedHeartMapper {

    private FeedHeartMapper() {
        throw new IllegalArgumentException("Utility Class");
    }

    public static FeedHeart makeFeedHeartWithFeedIdAndMemberId(FeedHeartId feedHeartId, FeedId feedId,
                                                               MemberId memberId) {
        return new FeedHeart(feedHeartId, feedId, memberId);
    }

    public static FeedHeartResponse toHeartResponse(String id, String feedId, String memberId, boolean isLiked,
                                                    int feedHeartCount) {
        return new FeedHeartResponse(id, feedId, memberId, isLiked, feedHeartCount);
    }

}
