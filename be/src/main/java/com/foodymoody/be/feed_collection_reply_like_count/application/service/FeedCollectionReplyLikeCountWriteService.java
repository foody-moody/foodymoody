package com.foodymoody.be.feed_collection_reply_like_count.application.service;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.feed_collection_reply_like_count.domain.FeedCollectionReplyLikeCount;
import com.foodymoody.be.feed_collection_reply_like_count.domain.FeedCollectionReplyLikeCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyLikeCountWriteService {

    private final FeedCollectionReplyLikeCountRepository repository;

    @Transactional
    public void increaseCount(FeedCollectionReplyId feedCollectionReplyId) {
        repository.increaseCount(feedCollectionReplyId);
    }

    @Transactional
    public void decreaseCount(FeedCollectionReplyId feedCollectionReplyId) {
        repository.decreaseCount(feedCollectionReplyId);
    }

    @Transactional
    public void save(FeedCollectionReplyLikeCount likeCount) {
        repository.save(likeCount);
    }

}
