package com.foodymoody.be.feed_like_count.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed_like_count.domain.FeedLikeCountRepository;
import com.foodymoody.be.feed_like_count.domain.entity.FeedLikeCount;
import com.foodymoody.be.feed_like_count.infra.persistence.jpa.FeedLikeCountJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedLikeCountRepositoryImpl implements FeedLikeCountRepository {

    private final FeedLikeCountJpaRepository feedLikeCountJpaRepository;

    @Override
    public FeedLikeCount save(FeedLikeCount feedHeartCount) {
        return feedLikeCountJpaRepository.save(feedHeartCount);
    }

    @Override
    public void deleteAll() {
        feedLikeCountJpaRepository.deleteAll();
    }

    @Override
    public void incrementFeedHeartCount(FeedId feedId) {
        feedLikeCountJpaRepository.incrementFeedHeartCount(feedId);
    }

    @Override
    public void decrementFeedHeartCount(FeedId feedId) {
        feedLikeCountJpaRepository.decrementFeedHeartCount(feedId);
    }

    @Override
    public Optional<FeedLikeCount> findByFeedId(FeedId feedId) {
        return feedLikeCountJpaRepository.findByFeedId(feedId);
    }

}
