package com.foodymoody.be.feed_collection_comment_like.infra.persistence;

import com.foodymoody.be.feed_collection_comment_like.domain.FeedCollectionCommentLike;
import com.foodymoody.be.feed_collection_comment_like.domain.FeedCollectionCommentLikeRepository;
import com.foodymoody.be.feed_collection_comment_like.infra.persistence.jpa.FeedCollectionCommentLikeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionCommentLikeRepositoryImpl implements FeedCollectionCommentLikeRepository {

    private final FeedCollectionCommentLikeJpaRepository repository;

    @Override
    public FeedCollectionCommentLike save(FeedCollectionCommentLike commentLike) {
        return repository.save(commentLike);
    }
}
