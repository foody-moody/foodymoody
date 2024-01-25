package com.foodymoody.be.feed_comment.persentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.response.MemberFeedCommentSummaryResponse;
import com.foodymoody.be.feed_comment.application.usecase.FeedCommentReadUseCase;
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

    private final FeedCommentReadUseCase useCase;

    @GetMapping("/api/comments")
    public ResponseEntity<Slice<MemberFeedCommentSummaryResponse>> fetchComments(
            @RequestParam FeedId feedId,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable,
            @CurrentMemberId MemberId memberId
    ) {
        if (memberId == null) {
            var response = useCase.fetchComments(feedId, pageable);
            return ResponseEntity.ok(response);
        }
        var response = useCase.fetchComments(feedId, pageable, memberId);
        return ResponseEntity.ok(response);
    }
}
