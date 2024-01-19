package com.foodymoody.be.feed_comment_like.infra.usecase;

import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.CommentReadService;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_comment_like.application.CommentHeartWriteService;
import com.foodymoody.be.feed_comment_like.domain.FeedCommentLike;
import com.foodymoody.be.feed_comment_like_count.application.CommentHeartCountWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentLikeWriteUseCase {

    private final CommentHeartWriteService commentHeartWriteService;
    private final CommentReadService commentReadService;
    private final CommentHeartCountWriteService commentHeartCountWriteService;

    @Transactional
    public void registerCommentLike(FeedCommentId feedCommentId, MemberId memberId) {
        FeedComment feedComment = commentReadService.fetchById(feedCommentId);
        if (commentHeartWriteService.existsByCommentIdAndMemberId(feedCommentId, memberId)) {
            return;
        }
        FeedCommentLike feedCommentLike = commentHeartWriteService.registerCommentHeart(feedCommentId, memberId);
        commentHeartCountWriteService.increment(feedCommentId);
        MemberId commentWriterId = feedComment.getMemberId();
        Events.raise(toCommentLikeAddedEvent(feedComment, feedCommentLike, commentWriterId));
    }

    @Transactional
    public void deleteCommentLike(FeedCommentId feedCommentId, MemberId memberId) {
        commentReadService.fetchById(feedCommentId);
        if (!commentHeartWriteService.existsByCommentIdAndMemberId(feedCommentId, memberId)) {
            return;
        }
        commentHeartWriteService.deleteCommentHeart(feedCommentId, memberId);
        commentHeartCountWriteService.decrement(feedCommentId);
    }

    private static FeedCommentLikeAddedEvent toCommentLikeAddedEvent(
            FeedComment feedComment, FeedCommentLike feedCommentLike,
            MemberId memberId
    ) {
        return FeedCommentLikeAddedEvent.of(
                feedComment.getFeedId(),
                feedComment.getId(),
                feedComment.getContent(),
                memberId,
                feedCommentLike.getMemberId(),
                feedCommentLike.getCreatedAt()
        );
    }
}
