package com.foodymoody.be.feed_like_count.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedLikeCountId;
import com.foodymoody.be.feed_like_count.domain.entity.FeedLikeCount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FeedLikeCountJpaRepository extends JpaRepository<FeedLikeCount, FeedLikeCountId> {

    @Modifying
    @Query("UPDATE FeedLikeCount _likeCount SET _likeCount.count = _likeCount.count + 1 WHERE _likeCount.feedId = :feedId")
    void incrementFeedHeartCount(FeedId feedId);

    @Modifying
    @Query("UPDATE FeedLikeCount _likeCount SET _likeCount.count = _likeCount.count - 1 WHERE _likeCount.feedId = :feedId")
    void decrementFeedHeartCount(FeedId feedId);

    Optional<FeedLikeCount> findByFeedId(FeedId feedId);

}
