package com.foodymoody.be.comment_heart.infra.usecase;

import com.foodymoody.be.comment.application.CommentReadService;
import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment_heart.application.CommentHeartWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentHeartWriteUseCase {

    private final CommentHeartWriteService commentHeartWriteService;
    private final CommentReadService commentReadService;

    public void registerCommentHeart(String commentIdValue, String memberId) {
        CommentId commentId = new CommentId(commentIdValue);
        commentReadService.validate(commentId);
        commentHeartWriteService.registerCommentHeart(commentId, memberId);
    }
}
