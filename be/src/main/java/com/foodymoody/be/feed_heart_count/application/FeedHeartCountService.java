package com.foodymoody.be.feed_heart_count.application;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_heart_count.domain.FeedHeartCountRepository;
import com.foodymoody.be.feed_heart_count.domain.entity.FeedHeartCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FeedHeartCountService {

    private final FeedHeartCountRepository feedHeartCountRepository;

    @Transactional
    public void save(FeedHeartCount feedHeartCount) {
        feedHeartCountRepository.save(feedHeartCount);
    }

    @Transactional
    public void incrementFeedHeartCount(String feedId) {
        feedHeartCountRepository.incrementFeedHeartCount(IdFactory.createFeedId(feedId));
    }

    @Transactional
    public void decrementFeedHeartCount(String feedId) {
        feedHeartCountRepository.decrementFeedHeartCount(IdFactory.createFeedId(feedId));
    }

    public FeedHeartCount findFeedHeartCountByFeedId(String feedId) {
        return feedHeartCountRepository.findByFeedId(IdFactory.createFeedId(feedId))
                .orElseThrow(() -> new IllegalArgumentException("해당 피드 좋아요 수 없음. feedId: " + feedId));
    }

}
