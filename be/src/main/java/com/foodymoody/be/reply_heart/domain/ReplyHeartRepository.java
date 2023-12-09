package com.foodymoody.be.reply_heart.domain;


import com.foodymoody.be.common.util.ids.ReplyId;

public interface ReplyHeartRepository {

    ReplyHeart save(ReplyHeart replyHeart);

    void deleteByReplyIdAndMemberId(ReplyId replyId, String memberId);

    boolean existsByReplyIdAndMemberId(ReplyId replyId, String memberId);
}
