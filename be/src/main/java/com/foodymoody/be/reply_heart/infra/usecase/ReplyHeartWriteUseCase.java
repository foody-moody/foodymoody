package com.foodymoody.be.reply_heart.infra.usecase;

import com.foodymoody.be.comment.application.ReplyReadService;
import com.foodymoody.be.comment.domain.entity.ReplyId;
import com.foodymoody.be.reply_heart.application.ReplyHeartWriteService;
import com.foodymoody.be.reply_heart_count.application.ReplyHeartCountWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyHeartWriteUseCase {

    private final ReplyHeartWriteService replyHeartWriteService;
    private final ReplyReadService replyReadService;
    private final ReplyHeartCountWriteService replyHeartCountWriteService;

    @Transactional
    public void registerReplyHeart(String replyIdValue, String memberId) {
        ReplyId replyId = new ReplyId(replyIdValue);
        replyReadService.validate(replyId);
        replyHeartWriteService.registerReplyHeart(replyId, memberId);
        replyHeartCountWriteService.increment(replyId);
    }

    @Transactional
    public void deleteReplyHeart(String replyIdValue, String memberId) {
        ReplyId replyId = new ReplyId(replyIdValue);
        replyReadService.validate(replyId);
        replyHeartWriteService.deleteReplyHeart(replyId, memberId);
        replyHeartCountWriteService.decrement(replyId);
    }
}
