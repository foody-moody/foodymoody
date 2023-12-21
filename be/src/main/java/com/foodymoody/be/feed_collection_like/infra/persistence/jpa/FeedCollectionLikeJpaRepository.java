package com.foodymoody.be.feed_collection_like.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_like.domain.FeedCollectionLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCollectionLikeJpaRepository extends JpaRepository<FeedCollectionLike, FeedCollectionLikeId> {

    void deleteByIdAndMemberId(FeedCollectionLikeId likeId, MemberId memberId);
}
