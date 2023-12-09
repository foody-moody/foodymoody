package com.foodymoody.be.reply_heart.application;

import com.foodymoody.be.common.util.ids.ReplyHeartId;
import com.foodymoody.be.common.util.ids.ReplyId;
import com.foodymoody.be.reply_heart.domain.ReplyHeart;
import com.foodymoody.be.reply_heart.domain.ReplyHeartIdFactory;
import com.foodymoody.be.reply_heart.domain.ReplyHeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyHeartWriteService {

    private final ReplyHeartRepository replyHeartRepository;

    @Transactional
    public void registerReplyHeart(ReplyId replyId, String memberId) {
        ReplyHeartId replyHeartId = ReplyHeartIdFactory.newId();
        ReplyHeart replyHeart = new ReplyHeart(replyHeartId, replyId, memberId);
        replyHeartRepository.save(replyHeart);
    }

    @Transactional
    public void deleteReplyHeart(ReplyId replyId, String memberId) {
        replyHeartRepository.deleteByReplyIdAndMemberId(replyId, memberId);
    }

    public boolean existsByReplyIdAndMemberId(ReplyId replyId, String memberId) {
        return replyHeartRepository.existsByReplyIdAndMemberId(replyId, memberId);
    }
}
