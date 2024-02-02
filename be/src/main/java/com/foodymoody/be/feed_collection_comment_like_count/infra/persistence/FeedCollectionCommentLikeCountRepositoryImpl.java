package com.foodymoody.be.feed_collection_comment_like_count.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.feed_collection_comment_like_count.domain.FeedCollectionCommentLikeCount;
import com.foodymoody.be.feed_collection_comment_like_count.domain.FeedCollectionCommentLikeCountRepository;
import com.foodymoody.be.feed_collection_comment_like_count.infra.persistence.jpa.FeedCollectionCommentLikeCountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionCommentLikeCountRepositoryImpl implements FeedCollectionCommentLikeCountRepository {

    private final FeedCollectionCommentLikeCountJpaRepository repository;

    @Override
    public void increase(FeedCollectionCommentId feedCollectionCommentId) {
        repository.increase(feedCollectionCommentId);
    }

    @Override
    public void decrease(FeedCollectionCommentId feedCollectionCommentId) {
        repository.decrease(feedCollectionCommentId);
    }

    @Override
    public FeedCollectionCommentLikeCount save(FeedCollectionCommentLikeCount likeCount) {
        return repository.save(likeCount);
    }
}
