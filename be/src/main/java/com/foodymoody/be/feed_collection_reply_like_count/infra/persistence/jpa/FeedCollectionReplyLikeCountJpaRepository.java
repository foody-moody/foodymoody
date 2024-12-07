package com.foodymoody.be.feed_collection_reply_like_count.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyLikeCountId;
import com.foodymoody.be.feed_collection_reply_like_count.domain.FeedCollectionReplyLikeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FeedCollectionReplyLikeCountJpaRepository extends
        JpaRepository<FeedCollectionReplyLikeCount, FeedCollectionReplyLikeCountId> {

    @Modifying
    @Query("UPDATE FeedCollectionReplyLikeCount _lickCount " +
            "SET _lickCount.count = _lickCount.count + 1 " +
            "WHERE _lickCount.feedCollectionReplyId = :feedCollectionReplyId")
    void increaseCount(FeedCollectionReplyId feedCollectionReplyId);

    @Modifying
    @Query("UPDATE FeedCollectionReplyLikeCount _lickCount " +
            "SET _lickCount.count = _lickCount.count - 1 " +
            "WHERE _lickCount.feedCollectionReplyId = :feedCollectionReplyId")
    void decreaseCount(FeedCollectionReplyId feedCollectionReplyId);

}
