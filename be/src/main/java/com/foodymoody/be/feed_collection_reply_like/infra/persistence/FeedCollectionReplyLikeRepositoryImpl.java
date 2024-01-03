package com.foodymoody.be.feed_collection_reply_like.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply_like.domain.FeedCollectionReplyLike;
import com.foodymoody.be.feed_collection_reply_like.domain.FeedCollectionReplyLikeRepository;
import com.foodymoody.be.feed_collection_reply_like.infra.persistence.jpa.FeedCollectionReplyLikeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionReplyLikeRepositoryImpl implements FeedCollectionReplyLikeRepository {

    private final FeedCollectionReplyLikeJpaRepository repository;

    @Override
    public FeedCollectionReplyLike save(FeedCollectionReplyLike like) {
        return repository.save(like);
    }

    @Override
    public void deleteByIdAndMemberId(FeedCollectionReplyLikeId id, MemberId memberId) {
        repository.deleteByIdAndMemberId(id, memberId);
    }
}
