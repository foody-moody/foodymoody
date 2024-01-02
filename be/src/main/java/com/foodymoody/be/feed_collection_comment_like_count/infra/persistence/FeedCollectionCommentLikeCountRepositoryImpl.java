package com.foodymoody.be.feed_collection_comment_like_count.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.feed_collection_comment_like_count.domain.FeedCollectionCommentLikeCount;
import com.foodymoody.be.feed_collection_comment_like_count.domain.FeedCollectionCommentLikeCountRepository;
import com.foodymoody.be.feed_collection_comment_like_count.infra.persistence.jpa.FeedCollectionCommentLikeCountJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionCommentLikeCountRepositoryImpl implements FeedCollectionCommentLikeCountRepository {

    private final FeedCollectionCommentLikeCountJpaRepository repository;

    @Override
    public Optional<FeedCollectionCommentLikeCount> findByFeedCollectionCommentId(
            FeedCollectionCommentId feedCollectionCommentId
    ) {
        return repository.findByFeedCollectionCommentId(feedCollectionCommentId);
    }

    @Override
    public void increaseCount(FeedCollectionCommentId feedCollectionCommentId) {
        repository.increaseCount(feedCollectionCommentId);
    }

    @Override
    public void decreaseCount(FeedCollectionCommentId feedCollectionCommentId) {
        repository.decreaseCount(feedCollectionCommentId);
    }

    @Override
    public FeedCollectionCommentLikeCount save(FeedCollectionCommentLikeCount likeCount) {
        return repository.save(likeCount);
    }
}
