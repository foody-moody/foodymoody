package com.foodymoody.be.feed_reply_like.application.usecase;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.service.FeedReplyReadService;
import com.foodymoody.be.feed_reply_like.application.service.FeedReplyLikeWriteService;
import com.foodymoody.be.feed_reply_like_count.application.service.ReplyHeartCountWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyHeartWriteUseCase {

    private final FeedReplyLikeWriteService feedReplyLikeWriteService;
    private final FeedReplyReadService feedReplyReadService;
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
        feedReplyReadService.validate(feedReplyId);
        if (!feedReplyLikeWriteService.existsByReplyIdAndMemberId(feedReplyId, memberId)) {
            return;
        }
        feedReplyLikeWriteService.deleteReplyLike(feedReplyId, memberId);
        replyHeartCountWriteService.decrement(feedReplyId);
    }
}
