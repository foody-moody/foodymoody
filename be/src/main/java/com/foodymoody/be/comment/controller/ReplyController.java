package com.foodymoody.be.comment.controller;

import com.foodymoody.be.comment.controller.dto.ReplyResponse;
import com.foodymoody.be.comment.service.ReplayService;
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

    private final ReplayService replayService;

    @GetMapping("/api/comments/{id}/replies")
    public ResponseEntity<Slice<ReplyResponse>> fetchAllReply(@PathVariable String id,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable,
            @MemberId String memberId) {
        var allReplay = replayService.fetchAllReplay(id, pageable);
        return ResponseEntity.ok(allReplay);
    }

}
