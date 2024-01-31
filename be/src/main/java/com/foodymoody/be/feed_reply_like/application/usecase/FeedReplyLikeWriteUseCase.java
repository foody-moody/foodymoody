package com.foodymoody.be.feed_reply_like.application.usecase;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_reply_like.application.service.FeedReplyLikeWriteService;
import com.foodymoody.be.feed_reply_like_count.application.service.ReplyHeartCountWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedReplyLikeWriteUseCase {

    private final FeedReplyLikeWriteService feedReplyLikeService;
    private final ReplyHeartCountWriteService replyHeartCountService;

    @Transactional
    public void register(FeedCommentId feedCommentId, FeedReplyId feedReplyId, MemberId memberId) {
        if (feedReplyLikeService.existsByReplyIdAndMemberId(feedReplyId, memberId)) {
            return;
        }
        feedReplyLikeService.register(feedCommentId, feedReplyId, memberId);
        replyHeartCountService.increment(feedReplyId);
    }

    @Transactional
    public void delete(FeedReplyId feedReplyId, MemberId memberId) {
        if (!feedReplyLikeService.existsByReplyIdAndMemberId(feedReplyId, memberId)) {
            return;
        }
        feedReplyLikeService.delete(feedReplyId, memberId);
        replyHeartCountService.decrement(feedReplyId);
    }
}
