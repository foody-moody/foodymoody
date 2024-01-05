package com.foodymoody.be.comment_heart.infra.usecase;

import com.foodymoody.be.comment.application.CommentReadService;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.comment_heart.application.CommentHeartWriteService;
import com.foodymoody.be.comment_heart.domain.CommentHeart;
import com.foodymoody.be.comment_heart_count.application.CommentHeartCountWriteService;
import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentHeartWriteUseCase {

    private final CommentHeartWriteService commentHeartWriteService;
    private final CommentReadService commentReadService;
    private final CommentHeartCountWriteService commentHeartCountWriteService;

    @Transactional
    public void registerCommentHeart(CommentId commentId, MemberId memberId) {
        Comment comment = commentReadService.fetchById(commentId);
        if (commentHeartWriteService.existsByCommentIdAndMemberId(commentId, memberId)) {
            return;
        }
        CommentHeart commentHeart = commentHeartWriteService.registerCommentHeart(commentId, memberId);
        commentHeartCountWriteService.increment(commentId);
        MemberId commentWriterId = comment.getMemberId();
        Events.raise(toCommentHeartAddedEvent(comment, commentHeart, commentWriterId));
    }

    @Transactional
    public void deleteCommentHeart(CommentId commentId, MemberId memberId) {
        commentReadService.fetchById(commentId);
        if (!commentHeartWriteService.existsByCommentIdAndMemberId(commentId, memberId)) {
            return;
        }
        commentHeartWriteService.deleteCommentHeart(commentId, memberId);
        commentHeartCountWriteService.decrement(commentId);
    }

    private static CommentHeartAddedEvent toCommentHeartAddedEvent(
            Comment comment, CommentHeart commentHeart,
            MemberId memberId
    ) {
        return new CommentHeartAddedEvent(
                comment.getFeedId(),
                comment.getContent(),
                NotificationType.COMMENT_LIKED_ADDED_EVENT,
                comment.getId(),
                memberId,
                commentHeart.getCreatedAt()
        );
    }
}
