package com.foodymoody.be.feed_comment.persentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.response.MemberFeedReplySummaryResponse;
import com.foodymoody.be.feed_comment.application.service.FeedReplyReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedReplyReadController {

    private final FeedReplyReadService service;

    @GetMapping("/api/feed/{ignoreFeedId}/comments/{feedCommentId}/replies")
    public ResponseEntity<Slice<MemberFeedReplySummaryResponse>> fetchAllReply(
            @PathVariable FeedId ignoreFeedId,
            @PathVariable FeedCommentId feedCommentId,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable,
            @CurrentMemberId MemberId memberId
    ) {
        if (memberId == null) {
            var response = service.fetchAllReply(feedCommentId, pageable);
            return ResponseEntity.ok(response);
        }
        var response = service.fetchAllReplyByMemberId(feedCommentId, memberId, pageable);
        return ResponseEntity.ok(response);
    }

}
