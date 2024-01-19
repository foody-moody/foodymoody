package com.foodymoody.be.feed_comment.persentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.IdResponse;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.CommentTranslator;
import com.foodymoody.be.feed_comment.application.CommentWriteService;
import com.foodymoody.be.feed_comment.application.dto.request.EditCommentRequest;
import com.foodymoody.be.feed_comment.application.dto.request.RegisterCommentRequest;
import com.foodymoody.be.feed_comment.application.dto.request.RegisterReplyRequest;
import com.foodymoody.be.feed_comment.infra.usecase.CommentUseCase;
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
    private final CommentTranslator commentTranslator;

    @PostMapping("/api/comments")
    public ResponseEntity<IdResponse> register(
            @Valid @RequestBody RegisterCommentRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        var data = commentTranslator.toRegisterCommentData(request, memberId);
        var id = commentUseCase.registerComment(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.of(id));
    }

    @PutMapping("/api/comments/{id}")
    public ResponseEntity<Void> edit(
            @PathVariable FeedCommentId id,
            @Valid @RequestBody EditCommentRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        var data = commentTranslator.toEditCommentData(request, id, memberId);
        commentWriteService.edit(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable FeedCommentId id,
            @CurrentMemberId MemberId memberId
    ) {
        commentWriteService.delete(id, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/comments/{id}")
    public ResponseEntity<IdResponse> reply(
            @PathVariable FeedCommentId id,
            @Valid @RequestBody RegisterReplyRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        var data = commentTranslator.toRegisterReplyData(request, memberId, id);
        var replyId = commentWriteService.reply(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.of(replyId));
    }
}