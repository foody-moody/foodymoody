package com.foodymoody.be.reply_heart_count.infra.persistence;

import com.foodymoody.be.common.util.ids.ReplyId;
import com.foodymoody.be.reply_heart_count.domain.ReplyHeartCount;
import com.foodymoody.be.reply_heart_count.domain.ReplyHeartCountRepository;
import com.foodymoody.be.reply_heart_count.infra.persistence.jpa.ReplyHeartCountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReplyHeartCountRepositoryImpl implements ReplyHeartCountRepository {

    private final ReplyHeartCountJpaRepository replyHeartCountJpaRepository;

    @Override
    public ReplyHeartCount save(ReplyHeartCount replyHeartCount) {
        return replyHeartCountJpaRepository.save(replyHeartCount);
    }

    @Override
    public void incrementCount(ReplyId replyId) {
        replyHeartCountJpaRepository.incrementCount(replyId);
    }

    @Override
    public void decrementCount(ReplyId replyId) {
        replyHeartCountJpaRepository.decrementCount(replyId);
    }
}
