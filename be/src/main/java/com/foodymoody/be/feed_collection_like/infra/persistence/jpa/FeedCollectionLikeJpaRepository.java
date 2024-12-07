package com.foodymoody.be.feed_collection_like.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_like.domain.FeedCollectionLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCollectionLikeJpaRepository extends JpaRepository<FeedCollectionLike, FeedCollectionLikeId> {

    Optional<FeedCollectionLike> findByFeedCollectionIdAndMemberId(
            FeedCollectionId feedCollectionId, MemberId memberId
    );

    boolean existsByFeedCollectionIdAndMemberId(FeedCollectionId feedCollectionId, MemberId memberId);

    void deleteByFeedCollectionIdAndMemberId(FeedCollectionId feedCollectionId, MemberId memberId);

}
