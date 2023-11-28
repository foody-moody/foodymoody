package com.foodymoody.be.comment.infra;

import com.foodymoody.be.comment.application.ReplyService;
import com.foodymoody.be.comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.comment.domain.entity.CommentId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberReplyUseCase {

    private final ReplyService replyService;

    public Slice<MemberReplySummary> fetchAllReply(String id, Pageable pageable) {
        var commentId = new CommentId(id);
        return replyService.fetchAllReply(commentId, pageable);
    }
}
