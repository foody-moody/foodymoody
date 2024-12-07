package com.foodymoody.be.feed_collection_reply_like.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
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
    public void deleteByFeedCollectionReplyIdAndMemberId(FeedCollectionReplyId id, MemberId memberId) {
        repository.deleteByFeedCollectionReplyIdAndMemberId(id, memberId);
    }

}
