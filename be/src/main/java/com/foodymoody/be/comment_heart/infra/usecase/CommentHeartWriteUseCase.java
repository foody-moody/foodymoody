package com.foodymoody.be.comment_heart.infra.usecase;

import com.foodymoody.be.comment.application.CommentReadService;
import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment_heart.application.CommentHeartWriteService;
import com.foodymoody.be.comment_heart_count.application.CommentHeartCountWriteService;
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
    public void registerCommentHeart(String commentIdValue, String memberId) {
        CommentId commentId = new CommentId(commentIdValue);
        commentReadService.validate(commentId);
        commentHeartWriteService.registerCommentHeart(commentId, memberId);
        commentHeartCountWriteService.increment(commentId);
    }

    @Transactional
    public void deleteCommentHeart(String commentIdValue, String memberId) {
        CommentId commentId = new CommentId(commentIdValue);
        commentReadService.validate(commentId);
        commentHeartWriteService.deleteCommentHeart(commentId, memberId);
        commentHeartCountWriteService.decrement(commentId);
    }
}
