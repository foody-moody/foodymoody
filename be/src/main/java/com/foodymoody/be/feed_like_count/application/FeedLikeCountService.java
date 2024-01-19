package com.foodymoody.be.feed_like_count.application;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_like_count.domain.entity.FeedLikeCount;
import com.foodymoody.be.feed_like_count.infra.persistence.jpa.FeedLikeCountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FeedLikeCountService {

    private final FeedLikeCountJpaRepository feedLikeCountJpaRepository;

    @Transactional
    public void save(FeedLikeCount feedLikeCount) {
        feedLikeCountJpaRepository.save(feedLikeCount);
    }

    @Transactional
    public void incrementFeedHeartCount(String feedId) {
        feedLikeCountJpaRepository.incrementFeedHeartCount(IdFactory.createFeedId(feedId));
    }

    @Transactional
    public void decrementFeedHeartCount(String feedId) {
        feedLikeCountJpaRepository.decrementFeedHeartCount(IdFactory.createFeedId(feedId));
    }

    public FeedLikeCount findFeedHeartCountByFeedId(String feedId) {
        return feedLikeCountJpaRepository.findByFeedId(IdFactory.createFeedId(feedId))
                .orElseThrow(() -> new IllegalArgumentException("해당 피드 좋아요 수 없음. feedId: " + feedId));
    }

}
