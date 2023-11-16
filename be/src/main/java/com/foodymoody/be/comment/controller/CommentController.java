package com.foodymoody.be.comment.controller;

import com.foodymoody.be.comment.domain.CommentId;
import com.foodymoody.be.comment.service.CommentService;
import com.foodymoody.be.common.annotation.MemberId;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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
}
