package com.foodymoody.be.feed_heart.util;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed_heart.domain.FeedHeart;
import com.foodymoody.be.feed_heart.dto.request.FeedHeartRequest;
import com.foodymoody.be.feed_heart.dto.request.FeedHeartServiceRequest;
import com.foodymoody.be.feed_heart.dto.response.FeedHeartResponse;

public class FeedHeartMapper {

    private FeedHeartMapper() {
        throw new IllegalArgumentException("Utility Class");
    }

    public static FeedHeartServiceRequest toFeedHeartServiceRequest(FeedHeartRequest feedHeartRequest,
            String memberId) {
        return new FeedHeartServiceRequest(feedHeartRequest.getFeedId(), memberId);
    }

    public static FeedHeart makeFeedHeartWithFeedIdAndMemberId(FeedId feedId, String memberId) {
        return new FeedHeart(IdGenerator.generate(), feedId, memberId);
    }

    public static FeedHeartResponse toHeartResponse(String id, String feedId, String memberId, boolean isLiked,
            int feedHeartCount) {
        return new FeedHeartResponse(id, feedId, memberId, isLiked, feedHeartCount);
    }

}
