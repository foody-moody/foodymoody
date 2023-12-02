package com.foodymoody.be.comment.infra.usecase;

import com.foodymoody.be.comment.application.ReplyReadService;
import com.foodymoody.be.comment.application.dto.response.MemberReplySummaryResponse;
import com.foodymoody.be.comment.domain.entity.CommentId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberReplyUseCase {

    private final ReplyReadService replyReadService;

    public Slice<MemberReplySummaryResponse> fetchAllReply(String id, Pageable pageable) {
        var commentId = new CommentId(id);
        return replyReadService.fetchAllReply(commentId, pageable);
    }
}
