package com.foodymoody.be.feed_reply_like.domain;


import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;

public interface FeedReplyLikeRepository {

    FeedReplyLike save(FeedReplyLike feedReplyLike);

    void deleteByReplyIdAndMemberId(FeedReplyId feedReplyId, MemberId memberId);

    boolean existsByReplyIdAndMemberId(FeedReplyId feedReplyId, MemberId memberId);
}
