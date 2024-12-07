package com.foodymoody.be.feed_comment_like_count.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedCommentLikeCountId;
import com.foodymoody.be.feed_comment_like_count.domain.FeedCommentLikeCount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FeedCommentLikeCountJpaRepository extends JpaRepository<FeedCommentLikeCount, FeedCommentLikeCountId> {

    Optional<FeedCommentLikeCount> findByFeedCommentId(FeedCommentId feedCommentId);

    @Modifying
    @Query("update FeedCommentLikeCount as _likeCount " +
            "set _likeCount.count = _likeCount.count + 1 " +
            "where _likeCount.feedCommentId = :feedCommentId")
    void incrementCount(FeedCommentId feedCommentId);

    @Modifying
    @Query("update FeedCommentLikeCount as _likeCount " +
            "set _likeCount.count = _likeCount.count - 1 " +
            "where _likeCount.feedCommentId = :feedCommentId")
    void decrementCount(FeedCommentId feedCommentId);

}
