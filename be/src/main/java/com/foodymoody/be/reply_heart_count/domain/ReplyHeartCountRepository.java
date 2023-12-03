package com.foodymoody.be.reply_heart_count.domain;

import com.foodymoody.be.comment.domain.entity.ReplyId;

public interface ReplyHeartCountRepository {

    ReplyHeartCount save(ReplyHeartCount replyHeartCount);

    void incrementCount(ReplyId replyId);

    void decrementCount(ReplyId replyId);
}
