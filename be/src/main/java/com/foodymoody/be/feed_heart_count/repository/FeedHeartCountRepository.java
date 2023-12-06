package com.foodymoody.be.feed_heart_count.repository;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed_heart_count.domain.FeedHeartCount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FeedHeartCountRepository extends JpaRepository<FeedHeartCount, String> {

    @Modifying
    @Query("UPDATE FeedHeartCount _heart SET _heart.count = _heart.count + 1 WHERE _heart.feedId = :feedId")
    void incrementFeedHeartCount(FeedId feedId);

    @Modifying
    @Query("UPDATE FeedHeartCount _heart SET _heart.count = _heart.count - 1 WHERE _heart.feedId = :feedId")
    void decrementFeedHeartCount(FeedId feedId);

    Optional<FeedHeartCount> findByFeedId(FeedId feedId);

}
