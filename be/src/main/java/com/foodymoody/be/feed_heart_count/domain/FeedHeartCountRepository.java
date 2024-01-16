package com.foodymoody.be.feed_heart_count.domain;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed_heart_count.domain.entity.FeedHeartCount;
import java.util.Optional;

public interface FeedHeartCountRepository {

    FeedHeartCount save(FeedHeartCount feedHeartCount);

    void deleteAll();

    void incrementFeedHeartCount(FeedId feedId);

    void decrementFeedHeartCount(FeedId feedId);

    Optional<FeedHeartCount> findByFeedId(FeedId feedId);
}
