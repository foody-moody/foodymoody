package com.foodymoody.be.feed_like_count.application;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_like_count.domain.FeedLikeCountRepository;
import com.foodymoody.be.feed_like_count.domain.entity.FeedLikeCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FeedLikeCountService {

    private final FeedLikeCountRepository feedLikeCountRepository;

    @Transactional
    public void save(FeedLikeCount feedLikeCount) {
        feedLikeCountRepository.save(feedLikeCount);
    }

    @Transactional
    public void incrementFeedHeartCount(String feedId) {
        feedLikeCountRepository.incrementFeedHeartCount(IdFactory.createFeedId(feedId));
    }

    @Transactional
    public void decrementFeedHeartCount(String feedId) {
        feedLikeCountRepository.decrementFeedHeartCount(IdFactory.createFeedId(feedId));
    }

    public FeedLikeCount findFeedHeartCountByFeedId(String feedId) {
        return feedLikeCountRepository.findByFeedId(IdFactory.createFeedId(feedId))
                .orElseThrow(() -> new IllegalArgumentException("해당 피드 좋아요 수 없음. feedId: " + feedId));
    }

}
