package com.foodymoody.be.comment.persentation;

import com.foodymoody.be.comment.application.CommentWriteService;
import com.foodymoody.be.comment.application.dto.request.EditCommentRequest;
import com.foodymoody.be.comment.application.dto.request.RegisterCommentRequest;
import com.foodymoody.be.comment.application.dto.request.RegisterReplyRequest;
import com.foodymoody.be.comment.infra.usecase.CommentUseCase;
import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.IdResponse;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.MemberId;
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
public class CommentWriteController {

    private final CommentWriteService commentWriteService;
    private final CommentUseCase commentUseCase;

    @PostMapping("/api/comments")
    public ResponseEntity<IdResponse> register(
            @Valid @RequestBody RegisterCommentRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        var id = commentUseCase.registerComment(request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.of(id));
    }

    @PutMapping("/api/comments/{id}")
    public ResponseEntity<Void> edit(
            @PathVariable CommentId id,
            @Valid @RequestBody EditCommentRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        commentWriteService.edit(id, request, memberId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable CommentId id,
            @CurrentMemberId MemberId memberId
    ) {
        commentWriteService.delete(id, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/comments/{id}")
    public ResponseEntity<IdResponse> reply(
            @PathVariable CommentId id,
            @Valid @RequestBody RegisterReplyRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        var replyId = commentWriteService.reply(id, request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.of(replyId));
    }
}
