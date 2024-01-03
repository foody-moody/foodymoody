package com.foodymoody.be.reply_heart.domain;


import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyId;

public interface ReplyHeartRepository {

    ReplyHeart save(ReplyHeart replyHeart);

    void deleteByReplyIdAndMemberId(ReplyId replyId, MemberId memberId);

    boolean existsByReplyIdAndMemberId(ReplyId replyId, MemberId memberId);
}
