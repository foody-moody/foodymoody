package com.foodymoody.be.feed_collection_like_count.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionLikeCountId;
import com.foodymoody.be.feed_collection_like_count.domain.FeedCollectionLikeCount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FeedCollectionLikeCountJpaRepository extends
        JpaRepository<FeedCollectionLikeCount, FeedCollectionLikeCountId> {

    @Modifying
    @Query("update FeedCollectionLikeCount _feedCollectionLikeCount " +
            "set _feedCollectionLikeCount.count = _feedCollectionLikeCount.count + 1 " +
            "where _feedCollectionLikeCount.feedCollectionId = :feedCollectionId")
    void increaseCount(FeedCollectionId feedCollectionId);

    @Modifying
    @Query("update FeedCollectionLikeCount _feedCollectionLikeCount " +
            "set _feedCollectionLikeCount.count = _feedCollectionLikeCount.count - 1 " +
            "where _feedCollectionLikeCount.feedCollectionId = :feedCollectionId")
    void decreaseCount(FeedCollectionId feedCollectionId);

    Optional<FeedCollectionLikeCount> findByFeedCollectionId(FeedCollectionId feedCollectionId);
}
