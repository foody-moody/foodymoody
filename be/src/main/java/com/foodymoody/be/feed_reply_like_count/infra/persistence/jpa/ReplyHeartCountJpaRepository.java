package com.foodymoody.be.feed_reply_like_count.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.ReplyHeartCountId;
import com.foodymoody.be.feed_reply_like_count.domain.ReplyHeartCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyHeartCountJpaRepository extends JpaRepository<ReplyHeartCount, ReplyHeartCountId> {

    @Modifying
    @Query("update ReplyHeartCount _replyHeartCount set _replyHeartCount.count = _replyHeartCount.count + 1 where _replyHeartCount.feedReplyId = :feedReplyId")
    void incrementCount(FeedReplyId feedReplyId);

    @Modifying
    @Query("update ReplyHeartCount _replyHeartCount set _replyHeartCount.count = _replyHeartCount.count - 1 where _replyHeartCount.feedReplyId = :feedReplyId")
    void decrementCount(FeedReplyId feedReplyId);
}
