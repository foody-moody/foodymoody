package com.foodymoody.be.feed_collection_like_count.application;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.feed_collection_like_count.domain.FeedCollectionLikeCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionLikeCountReadService {

    private final FeedCollectionLikeCountRepository repository;

    @Transactional(readOnly = true)
    public long getCountByFeedCollectionId(FeedCollectionId feedCollectionId) {
        var feedCollectionLikeCount = repository.findByFeedCollectionId(feedCollectionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피드 컬렉션 카운터입니다."));
        return feedCollectionLikeCount.getLikeCount();
    }
}
