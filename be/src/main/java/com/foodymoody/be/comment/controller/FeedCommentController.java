package com.foodymoody.be.comment.controller;

import com.foodymoody.be.comment.controller.dto.CommentResponse;
import com.foodymoody.be.comment.service.FeedCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCommentController {

    private final FeedCommentService feedCommentService;

    @GetMapping("/api/feeds/{feedId}/comments")
    public ResponseEntity<Slice<CommentResponse>> fetchComments(@PathVariable String feedId, Pageable pageable) {
        Slice<CommentResponse> comments = feedCommentService.fetchComments(feedId, pageable);
        return ResponseEntity.ok(comments);
    }
}
