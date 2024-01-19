package com.foodymoody.be.feed_heart_count.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed_heart_count.domain.FeedHeartCountRepository;
import com.foodymoody.be.feed_heart_count.infra.persistence.jpa.FeedHeartCountJpaRepository;
import com.foodymoody.be.feed_like_count.domain.entity.FeedLikeCount;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedHeartCountRepositoryImpl implements FeedHeartCountRepository {

    private final FeedHeartCountJpaRepository feedHeartCountJpaRepository;

    @Override
    public FeedLikeCount save(FeedLikeCount feedHeartCount) {
        return feedHeartCountJpaRepository.save(feedHeartCount);
    }

    @Override
    public void deleteAll() {
        feedHeartCountJpaRepository.deleteAll();
    }

    @Override
    public void incrementFeedHeartCount(FeedId feedId) {
        feedHeartCountJpaRepository.incrementFeedHeartCount(feedId);
    }

    @Override
    public void decrementFeedHeartCount(FeedId feedId) {
        feedHeartCountJpaRepository.decrementFeedHeartCount(feedId);
    }

    @Override
    public Optional<FeedLikeCount> findByFeedId(FeedId feedId) {
        return feedHeartCountJpaRepository.findByFeedId(feedId);
    }

}
