package com.foodymoody.be.reply_heart.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyId;
import com.foodymoody.be.reply_heart.infra.usecase.ReplyHeartWriteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReplyHeartWriteController {

    private final ReplyHeartWriteUseCase useCase;

    @PostMapping("/api/comments/{commentId}/replies/{replyId}/likes")
    public ResponseEntity<Void> create(
            @PathVariable CommentId commentId,
            @PathVariable ReplyId replyId,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.registerReplyHeart(commentId, replyId, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/api/comments/{ignoredCommentId}/replies/{replyId}/likes")
    public ResponseEntity<Void> delete(
            @PathVariable CommentId ignoredCommentId,
            @PathVariable ReplyId replyId,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.deleteReplyHeart(replyId, memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
