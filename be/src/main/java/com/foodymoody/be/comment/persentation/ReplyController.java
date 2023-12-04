package com.foodymoody.be.comment.persentation;

import com.foodymoody.be.comment.application.dto.response.MemberReplySummaryResponse;
import com.foodymoody.be.comment.infra.usecase.MemberReplyUseCase;
import com.foodymoody.be.common.annotation.MemberId;
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

    @GetMapping("/api/comments/{id}/replies")
    public ResponseEntity<Slice<MemberReplySummaryResponse>> fetchAllReply(
            @PathVariable String id,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable,
            @MemberId String memberId
    ) {
        if (memberId != null) {
            var allReplay = memberReplyUseCase.fetchAllReplyByMemberId(id, memberId, pageable);
            return ResponseEntity.ok(allReplay);
        }
        var allReplay = memberReplyUseCase.fetchAllReply(id, pageable);
        return ResponseEntity.ok(allReplay);
    }
}
