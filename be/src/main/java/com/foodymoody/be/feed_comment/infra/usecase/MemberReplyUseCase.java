package com.foodymoody.be.feed_comment.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.ReplyReadService;
import com.foodymoody.be.feed_comment.application.dto.response.MemberReplySummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberReplyUseCase {

    private final ReplyReadService replyReadService;

    public Slice<MemberReplySummaryResponse> fetchAllReply(FeedCommentId feedCommentId, Pageable pageable) {
        return replyReadService.fetchAllReply(feedCommentId, pageable);
    }

    public Slice<MemberReplySummaryResponse> fetchAllReplyByMemberId(
            FeedCommentId feedCommentId,
            MemberId memberId,
            Pageable pageable
    ) {
        return replyReadService.fetchAllReplyByMemberId(feedCommentId, memberId, pageable);
    }
}
