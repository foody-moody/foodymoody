package com.foodymoody.be.feed_like_count.domain;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedLikeCountId;
import com.foodymoody.be.feed_like_count.domain.entity.FeedLikeCount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FeedLikeCountRepository extends JpaRepository<FeedLikeCount, FeedLikeCountId> {

    @Modifying
    @Query("UPDATE FeedLikeCount _heart SET _heart.count = _heart.count + 1 WHERE _heart.feedId = :feedId")
    void incrementFeedHeartCount(FeedId feedId);

    @Modifying
    @Query("UPDATE FeedLikeCount _heart SET _heart.count = _heart.count - 1 WHERE _heart.feedId = :feedId")
    void decrementFeedHeartCount(FeedId feedId);

    Optional<FeedLikeCount> findByFeedId(FeedId feedId);

}
