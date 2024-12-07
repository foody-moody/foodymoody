package com.foodymoody.be.feed_comment_like.application.usecase;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.service.FeedCommentReadService;
import com.foodymoody.be.feed_comment_like.application.service.FeedCommentLikeWriteService;
import com.foodymoody.be.feed_comment_like_count.application.service.FeedCommentLikeCountWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCommentLikeWriteUseCase {

    private final FeedCommentLikeWriteService feedCommentLikeService;
    private final FeedCommentReadService feedCommentService;
    private final FeedCommentLikeCountWriteService feedCommentLikeCountService;

    @Transactional
    public void registerCommentLike(FeedCommentId feedCommentId, MemberId memberId) {
        feedCommentService.findById(feedCommentId);
        feedCommentLikeService.register(feedCommentId, memberId);
        feedCommentLikeCountService.increment(feedCommentId);
    }

    @Transactional
    public void deleteCommentLike(FeedCommentId feedCommentId, MemberId memberId) {
        feedCommentService.findById(feedCommentId);
        feedCommentLikeService.delete(feedCommentId, memberId);
        feedCommentLikeCountService.decrement(feedCommentId);
    }

}
