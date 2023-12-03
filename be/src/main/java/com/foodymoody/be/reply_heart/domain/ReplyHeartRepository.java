package com.foodymoody.be.reply_heart.domain;

import com.foodymoody.be.comment.domain.entity.ReplyId;

public interface ReplyHeartRepository {

    ReplyHeart save(ReplyHeart replyHeart);

    void deleteByReplyIdAndMemberId(ReplyId replyId, String memberId);
}
