package com.foodymoody.be.reply_heart.infra.usecase;

import com.foodymoody.be.comment.application.CommentReadService;
import com.foodymoody.be.comment.application.ReplyReadService;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.comment.domain.entity.Reply;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyId;
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
    private final CommentReadService commentReadService;

    @Transactional
    public void registerReplyHeart(String commentIdValue, String replyIdValue, String memberId) {
        MemberId memberWriterId = new MemberId(memberId);
        ReplyId replyId = IdFactory.createReplyId(replyIdValue);
        Reply reply = replyReadService.fetchById(replyId);
        if (replyHeartWriteService.existsByReplyIdAndMemberId(replyId, memberWriterId)) {
            return;
        }
        replyHeartWriteService.registerReplyHeart(replyId, memberWriterId);
        replyHeartCountWriteService.increment(replyId);
        Comment comment = commentReadService.fetchById(new CommentId(commentIdValue));
        new ReplyHeartAddedEvent(comment.getFeedId(), reply.getContent(), NotificationType.FEED_LIKED_ADDED_EVENT,
                                 comment.getId(),
                                 reply.getId(), memberWriterId, reply.getCreatedAt()
        );
    }

    @Transactional
    public void deleteReplyHeart(String commentIdValue, String replyIdValue, String memberIdValue) {
        ReplyId replyId = IdFactory.createReplyId(replyIdValue);
        MemberId memberId = IdFactory.createMemberId(memberIdValue);
        replyReadService.validate(replyId);
        if (!replyHeartWriteService.existsByReplyIdAndMemberId(replyId, memberId)) {
            return;
        }
        replyHeartWriteService.deleteReplyHeart(replyId, memberId);
        replyHeartCountWriteService.decrement(replyId);
    }
}
