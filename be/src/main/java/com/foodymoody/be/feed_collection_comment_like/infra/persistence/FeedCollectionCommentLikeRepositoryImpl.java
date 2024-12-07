package com.foodymoody.be.feed_collection_comment_like.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
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

    @Override
    public void deleteByCommentIdAndMemberId(FeedCollectionCommentId commentId, MemberId memberId) {
        repository.deleteByCommentIdAndMemberId(commentId, memberId);
    }

}
