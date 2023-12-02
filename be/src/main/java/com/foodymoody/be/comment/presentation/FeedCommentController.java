package com.foodymoody.be.comment.presentation;

import com.foodymoody.be.comment.application.dto.response.MemberCommentSummaryResponse;
import com.foodymoody.be.comment.infra.usecase.FeedCommentReadUseCase;
import com.foodymoody.be.common.annotation.MemberId;
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
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable,
            @MemberId String memberId
    ) {

        Slice<MemberCommentSummaryResponse> comments;
        if (memberId == null) {
            comments = feedCommentReadUseCase.fetchComments(feedId, pageable);
        } else {
            comments = feedCommentReadUseCase.fetchComments(feedId, pageable, memberId);
        }
        return ResponseEntity.ok(comments);
    }
}
