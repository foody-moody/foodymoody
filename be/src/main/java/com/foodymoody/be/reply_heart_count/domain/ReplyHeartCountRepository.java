package com.foodymoody.be.reply_heart_count.domain;


import com.foodymoody.be.common.util.ids.ReplyId;

public interface ReplyHeartCountRepository {

    ReplyHeartCount save(ReplyHeartCount replyHeartCount);

    void incrementCount(ReplyId replyId);

    void decrementCount(ReplyId replyId);
}
