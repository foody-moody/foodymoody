package com.foodymoody.be.feed_collection_like_count.application.service;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.feed_collection_like_count.domain.FeedCollectionLikeCount;
import com.foodymoody.be.feed_collection_like_count.domain.FeedCollectionLikeCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionLikeCountWriteService {

    private final FeedCollectionLikeCountRepository repository;

    @Transactional
    public void increaseLikeCount(FeedCollectionId feedCollectionId) {
        repository.increaseCount(feedCollectionId);
    }

    @Transactional
    public void decreaseLikeCount(FeedCollectionId feedCollectionId) {
        repository.decreaseCount(feedCollectionId);
    }

    @Transactional
    public void save(FeedCollectionLikeCount likeCount) {
        repository.save(likeCount);
    }

}
