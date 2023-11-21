package com.foodymoody.be.comment.controller;

import com.foodymoody.be.comment.controller.dto.EditCommentRequest;
import com.foodymoody.be.comment.controller.dto.RegisterCommentRequest;
import com.foodymoody.be.comment.controller.dto.RegisterReplyRequest;
import com.foodymoody.be.comment.controller.dto.ReplyResponse;
import com.foodymoody.be.comment.domain.CommentId;
import com.foodymoody.be.comment.service.CommentService;
import com.foodymoody.be.common.annotation.MemberId;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/comments")
    public ResponseEntity<CommentId> register(@Valid @RequestBody RegisterCommentRequest request,
            @MemberId String memberId) {
        CommentId id = commentService.registerComment(request, memberId);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/api/comments/{id}")
    public ResponseEntity<Void> edit(@PathVariable String id, @Valid @RequestBody EditCommentRequest request,
            @MemberId String memberId) {
        commentService.edit(id, request, memberId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id,
            @MemberId String memberId) {
        commentService.delete(id, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/comments/{id}")
    public ResponseEntity<Void> reply(@PathVariable String id, @Valid @RequestBody RegisterReplyRequest request,
            @MemberId String memberId) {
        commentService.reply(id, request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/comments/{id}/replies")
    public ResponseEntity<Slice<ReplyResponse>> fetchAllReply(@PathVariable String id,
            @PageableDefault Pageable pageable) {
        var allReplay = commentService.fetchAllReplay(id, pageable);
        return ResponseEntity.ok(allReplay);
    }
}
