package com.foodymoody.be.feed_collection_like.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCollectionLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_like.domain.FeedCollectionLike;
import com.foodymoody.be.feed_collection_like.domain.FeedCollectionLikeRepository;
import com.foodymoody.be.feed_collection_like.infra.persistence.jpa.FeedCollectionLikeJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionLikeRepositoryImpl implements FeedCollectionLikeRepository {

    private final FeedCollectionLikeJpaRepository feedCollectionLikeJpaRepository;

    @Override
    public FeedCollectionLike save(FeedCollectionLike like) {
        return feedCollectionLikeJpaRepository.save(like);
    }

    @Override
    public Optional<FeedCollectionLike> findById(FeedCollectionLikeId likeId) {
        return feedCollectionLikeJpaRepository.findById(likeId);
    }

    @Override
    public void deleteByIdAndMemberId(FeedCollectionLikeId likeId, MemberId memberId) {
        feedCollectionLikeJpaRepository.deleteByIdAndMemberId(likeId, memberId);
    }
}