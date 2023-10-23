package com.foodymoody.be.comment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @PostMapping("/api/comments")
    public ResponseEntity<Void> registerComment(@RequestBody RegisterCommentRequest request) {
        return ResponseEntity.ok().build();
    }
}
