package com.foodymoody.be.comment.controller;

import com.foodymoody.be.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/comments")
    public ResponseEntity<Void> registerComment(@RequestBody RegisterCommentRequest request) {
        commentService.registerComment(request);
        return ResponseEntity.ok().build();
    }
}
