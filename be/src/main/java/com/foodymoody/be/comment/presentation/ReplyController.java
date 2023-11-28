package com.foodymoody.be.comment.presentation;

import com.foodymoody.be.comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.comment.infra.MemberReplyUseCase;
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
    public ResponseEntity<Slice<MemberReplySummary>> fetchAllReply(@PathVariable String id,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable) {
        var allReplay = memberReplyUseCase.fetchAllReply(id, pageable);
        return ResponseEntity.ok(allReplay);
    }

}
