package com.foodymoody.be.comment.infra.usecase;

import com.foodymoody.be.comment.application.ReplyReadService;
import com.foodymoody.be.comment.application.dto.response.MemberReplySummaryResponse;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberReplyUseCase {

    private final ReplyReadService replyReadService;

    public Slice<MemberReplySummaryResponse> fetchAllReply(CommentId commentId, Pageable pageable) {
        return replyReadService.fetchAllReply(commentId, pageable);
    }

    public Slice<MemberReplySummaryResponse> fetchAllReplyByMemberId(
            CommentId commentId,
            MemberId memberId,
            Pageable pageable
    ) {
        return replyReadService.fetchAllReplyByMemberId(commentId, memberId, pageable);
    }
}
