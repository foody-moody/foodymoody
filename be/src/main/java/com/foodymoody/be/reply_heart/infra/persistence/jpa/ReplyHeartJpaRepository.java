package com.foodymoody.be.reply_heart.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyHeartId;
import com.foodymoody.be.common.util.ids.ReplyId;
import com.foodymoody.be.reply_heart.domain.ReplyHeart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyHeartJpaRepository extends JpaRepository<ReplyHeart, ReplyHeartId> {

    void deleteByReplyIdAndMemberId(ReplyId replyId, MemberId memberId);

    boolean existsByReplyIdAndMemberId(ReplyId replyId, MemberId memberId);
}
