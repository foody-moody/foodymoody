package com.foodymoody.be.reply_heart.infra.persistence.jpa;

import com.foodymoody.be.comment.domain.entity.ReplyId;
import com.foodymoody.be.reply_heart.domain.ReplyHeart;
import com.foodymoody.be.reply_heart.domain.ReplyHeartId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyHeartJpaRepository extends JpaRepository<ReplyHeart, ReplyHeartId> {

    void deleteByReplyIdAndMemberId(ReplyId replyId, String memberId);

    boolean existsByReplyIdAndMemberId(ReplyId replyId, String memberId);
}
