package com.foodymoody.be.feed_heart_count.domain;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed_like_count.domain.entity.FeedLikeCount;
import java.util.Optional;

public interface FeedHeartCountRepository {

    FeedLikeCount save(FeedLikeCount feedHeartCount);

    void deleteAll();

    void incrementFeedHeartCount(FeedId feedId);

    void decrementFeedHeartCount(FeedId feedId);

    Optional<FeedLikeCount> findByFeedId(FeedId feedId);
}
