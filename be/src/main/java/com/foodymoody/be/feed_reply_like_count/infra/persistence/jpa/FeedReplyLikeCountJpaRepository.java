package com.foodymoody.be.feed_reply_like_count.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.FeedReplyLikeCountId;
import com.foodymoody.be.feed_reply_like_count.domain.FeedReplyLikeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FeedReplyLikeCountJpaRepository extends JpaRepository<FeedReplyLikeCount, FeedReplyLikeCountId> {

    @Modifying
    @Query("update FeedReplyLikeCount _replyLikeCount " +
            "set _replyLikeCount.count = _replyLikeCount.count + 1 " +
            "where _replyLikeCount.feedReplyId = :feedReplyId")
    void incrementCount(FeedReplyId feedReplyId);

    @Modifying
    @Query("update FeedReplyLikeCount _replyLikeCount " +
            "set _replyLikeCount.count = _replyLikeCount.count - 1 " +
            "where _replyLikeCount.feedReplyId = :feedReplyId")
    void decrementCount(FeedReplyId feedReplyId);

}
