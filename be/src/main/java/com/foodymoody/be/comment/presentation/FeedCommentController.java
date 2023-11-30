package com.foodymoody.be.comment.presentation;

import com.foodymoody.be.comment.application.dto.response.MemberCommentSummaryResponse;
import com.foodymoody.be.comment.infra.FeedCommentReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCommentController {

    private final FeedCommentReadUseCase feedCommentReadUseCase;

    @GetMapping("/api/comments")
    public ResponseEntity<Slice<MemberCommentSummaryResponse>> fetchComments(
            @RequestParam String feedId,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {
        var comments = feedCommentReadUseCase.fetchComments(feedId, pageable);
        return ResponseEntity.ok(comments);
    }
}
