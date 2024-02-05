package com.foodymoody.be.feed_reply_like_count.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.feed_reply_like_count.domain.ReplyHeartCount;
import com.foodymoody.be.feed_reply_like_count.domain.ReplyHeartCountRepository;
import com.foodymoody.be.feed_reply_like_count.infra.persistence.jpa.ReplyHeartCountJpaRepository;
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
    public void incrementCount(FeedReplyId feedReplyId) {
        replyHeartCountJpaRepository.incrementCount(feedReplyId);
    }

    @Override
    public void decrementCount(FeedReplyId feedReplyId) {
        replyHeartCountJpaRepository.decrementCount(feedReplyId);
    }
}
