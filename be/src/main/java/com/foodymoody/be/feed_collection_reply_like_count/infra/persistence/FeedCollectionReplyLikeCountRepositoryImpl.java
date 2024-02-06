package com.foodymoody.be.feed_collection_reply_like_count.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.feed_collection_reply_like_count.domain.FeedCollectionReplyLikeCount;
import com.foodymoody.be.feed_collection_reply_like_count.domain.FeedCollectionReplyLikeCountRepository;
import com.foodymoody.be.feed_collection_reply_like_count.infra.persistence.jpa.FeedCollectionReplyLikeCountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionReplyLikeCountRepositoryImpl implements FeedCollectionReplyLikeCountRepository {

    private final FeedCollectionReplyLikeCountJpaRepository repository;

    @Override
    public void increaseCount(FeedCollectionReplyId feedCollectionReplyId) {
        repository.increaseCount(feedCollectionReplyId);
    }

    @Override
    public void decreaseCount(FeedCollectionReplyId feedCollectionReplyId) {
        repository.decreaseCount(feedCollectionReplyId);
    }

    @Override
    public FeedCollectionReplyLikeCount save(FeedCollectionReplyLikeCount likeCount) {
        return repository.save(likeCount);
    }
}
