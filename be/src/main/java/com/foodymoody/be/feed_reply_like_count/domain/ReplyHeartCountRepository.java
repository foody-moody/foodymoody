package com.foodymoody.be.feed_reply_like_count.domain;


import com.foodymoody.be.common.util.ids.FeedReplyId;

public interface ReplyHeartCountRepository {

    ReplyHeartCount save(ReplyHeartCount replyHeartCount);

    void incrementCount(FeedReplyId feedReplyId);

    void decrementCount(FeedReplyId feedReplyId);
}
