package com.foodymoody.be.feed_reply_like.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.ReplyReadService;
import com.foodymoody.be.feed_reply_like.application.FeedReplyLikeWriteService;
import com.foodymoody.be.feed_reply_like_count.application.ReplyHeartCountWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyHeartWriteUseCase {

    private final FeedReplyLikeWriteService feedReplyLikeWriteService;
    private final ReplyReadService replyReadService;
    private final ReplyHeartCountWriteService replyHeartCountWriteService;

    @Transactional
    public void registerReplyHeart(FeedCommentId feedCommentId, FeedReplyId feedReplyId, MemberId memberId) {
        if (feedReplyLikeWriteService.existsByReplyIdAndMemberId(feedReplyId, memberId)) {
            return;
        }
        feedReplyLikeWriteService.registerReplyLike(feedCommentId, feedReplyId, memberId);
        replyHeartCountWriteService.increment(feedReplyId);
    }

    @Transactional
    public void deleteReplyHeart(FeedReplyId feedReplyId, MemberId memberId) {
        replyReadService.validate(feedReplyId);
        if (!feedReplyLikeWriteService.existsByReplyIdAndMemberId(feedReplyId, memberId)) {
            return;
        }
        feedReplyLikeWriteService.deleteReplyLike(feedReplyId, memberId);
        replyHeartCountWriteService.decrement(feedReplyId);
    }
}
