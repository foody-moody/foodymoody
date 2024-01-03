package com.foodymoody.be.reply_heart.infra.persistence;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyId;
import com.foodymoody.be.reply_heart.domain.ReplyHeart;
import com.foodymoody.be.reply_heart.domain.ReplyHeartRepository;
import com.foodymoody.be.reply_heart.infra.persistence.jpa.ReplyHeartJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReplyHeartRepositoryImpl implements ReplyHeartRepository {

    private final ReplyHeartJpaRepository repository;

    @Override
    public ReplyHeart save(ReplyHeart replyHeart) {
        return repository.save(replyHeart);
    }

    @Override
    public void deleteByReplyIdAndMemberId(ReplyId replyId, MemberId memberId) {
        repository.deleteByReplyIdAndMemberId(replyId, memberId);
    }

    @Override
    public boolean existsByReplyIdAndMemberId(ReplyId replyId, MemberId memberId) {
        return repository.existsByReplyIdAndMemberId(replyId, memberId);
    }
}
