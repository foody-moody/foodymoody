package com.foodymoody.be.reply_heart_count.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.ReplyHeartCountId;
import com.foodymoody.be.common.util.ids.ReplyId;
import com.foodymoody.be.reply_heart_count.domain.ReplyHeartCount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyHeartCountJpaRepository extends JpaRepository<ReplyHeartCount, ReplyHeartCountId> {

    Optional<ReplyHeartCount> findByReplyId(ReplyId replyId);

    @Modifying
    @Query("update ReplyHeartCount _replyHeartCount set _replyHeartCount.count = _replyHeartCount.count + 1 where _replyHeartCount.replyId = :replyId")
    void incrementCount(ReplyId replyId);

    @Modifying
    @Query("update ReplyHeartCount _replyHeartCount set _replyHeartCount.count = _replyHeartCount.count - 1 where _replyHeartCount.replyId = :replyId")
    void decrementCount(ReplyId replyId);
}
