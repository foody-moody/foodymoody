package com.foodymoody.be.comment_heart.presentation;

import com.foodymoody.be.comment_heart.infra.usecase.CommentHeartWriteUseCase;
import com.foodymoody.be.common.annotation.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentHeartWriteController {

    private final CommentHeartWriteUseCase useCase;

    @PostMapping("/api/comments/{commentId}/hearts")
    public ResponseEntity<Void> create(@PathVariable String commentId, @MemberId String memberId) {
        useCase.registerCommentHeart(commentId, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/api/comments/{commentId}/hearts")
    public ResponseEntity<Void> delete(@PathVariable String commentId, @MemberId String memberId) {
        useCase.deleteCommentHeart(commentId, memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
