package com.foodymoody.be.feed_collection_comment_like_count.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentLikeCountId;
import com.foodymoody.be.feed_collection_comment_like_count.domain.FeedCollectionCommentLikeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FeedCollectionCommentLikeCountJpaRepository extends
        JpaRepository<FeedCollectionCommentLikeCount, FeedCollectionCommentLikeCountId> {

    @Modifying
    @Query("UPDATE FeedCollectionCommentLikeCount _likeCount " +
            "SET _likeCount.count = _likeCount.count + 1 " +
            "WHERE _likeCount.feedCollectionCommentId = :feedCollectionCommentId")
    void increase(FeedCollectionCommentId feedCollectionCommentId);

    @Modifying
    @Query("UPDATE FeedCollectionCommentLikeCount _likeCount " +
            "SET _likeCount.count = _likeCount.count - 1 " +
            "WHERE _likeCount.feedCollectionCommentId = :feedCollectionCommentId")
    void decrease(FeedCollectionCommentId feedCollectionCommentId);

}
