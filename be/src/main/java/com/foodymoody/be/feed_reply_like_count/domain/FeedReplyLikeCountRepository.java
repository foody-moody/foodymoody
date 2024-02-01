package com.foodymoody.be.feed_reply_like_count.domain;


import com.foodymoody.be.common.util.ids.FeedReplyId;

public interface FeedReplyLikeCountRepository {

    FeedReplyLikeCount save(FeedReplyLikeCount feedReplyLikeCount);

    void incrementCount(FeedReplyId feedReplyId);

    void decrementCount(FeedReplyId feedReplyId);
}
