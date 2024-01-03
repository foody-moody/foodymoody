package com.foodymoody.be.feed_collection_comment_like_count.application;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.feed_collection_comment_like_count.domain.FeedCollectionCommentLikeCount;
import com.foodymoody.be.feed_collection_comment_like_count.domain.FeedCollectionCommentLikeCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentLikeCountWriteService {

    private final FeedCollectionCommentLikeCountRepository repository;

    @Transactional
    public void increaseLikeCount(FeedCollectionCommentId feedCollectionCommentId) {
        repository.increaseCount(feedCollectionCommentId);
    }

    @Transactional
    public void decreaseLikeCount(FeedCollectionCommentId feedCollectionCommentId) {
        repository.decreaseCount(feedCollectionCommentId);
    }

    @Transactional
    public void save(FeedCollectionCommentLikeCount likeCount) {
        repository.save(likeCount);
    }
}
