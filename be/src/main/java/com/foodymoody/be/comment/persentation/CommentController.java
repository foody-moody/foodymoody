package com.foodymoody.be.comment.persentation;

import com.foodymoody.be.comment.application.CommentWriteService;
import com.foodymoody.be.comment.application.dto.request.EditCommentRequest;
import com.foodymoody.be.comment.application.dto.request.RegisterCommentRequest;
import com.foodymoody.be.comment.application.dto.request.RegisterReplyRequest;
import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment.infra.usecase.CommentUseCase;
import com.foodymoody.be.common.annotation.MemberId;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentWriteService commentWriteService;
    private final CommentUseCase commentUseCase;

    @PostMapping("/api/comments")
    public ResponseEntity<CommentId> register(@Valid @RequestBody RegisterCommentRequest request,
            @MemberId String memberId) {
        CommentId id = commentUseCase.registerComment(request, memberId);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/api/comments/{id}")
    public ResponseEntity<Void> edit(@PathVariable String id, @Valid @RequestBody EditCommentRequest request,
            @MemberId String memberId) {
        commentWriteService.edit(id, request, memberId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, @MemberId String memberId) {
        commentWriteService.delete(id, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/comments/{id}")
    public ResponseEntity<Void> reply(@PathVariable String id, @Valid @RequestBody RegisterReplyRequest request,
            @MemberId String memberId) {
        commentWriteService.reply(id, request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
