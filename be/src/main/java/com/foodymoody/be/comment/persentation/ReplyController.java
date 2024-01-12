package com.foodymoody.be.comment.persentation;

import com.foodymoody.be.comment.application.dto.response.MemberReplySummaryResponse;
import com.foodymoody.be.comment.infra.usecase.MemberReplyUseCase;
import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.MemberId;
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
public class ReplyController {

    private final MemberReplyUseCase memberReplyUseCase;

    @GetMapping("/api/comments/{commentId}/replies")
    public ResponseEntity<Slice<MemberReplySummaryResponse>> fetchAllReply(
            @PathVariable CommentId commentId,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable,
            @CurrentMemberId MemberId memberId
    ) {
        if (memberId == null) {
            var response = memberReplyUseCase.fetchAllReply(commentId, pageable);
            return ResponseEntity.ok(response);
        }
        var response = memberReplyUseCase.fetchAllReplyByMemberId(commentId, memberId, pageable);
        return ResponseEntity.ok(response);
    }
}
