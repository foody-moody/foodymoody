package com.foodymoody.be.comment.application;

import com.foodymoody.be.comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.comment.application.dto.response.MemberReplySummaryResponse;
import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment.domain.entity.ReplyId;
import com.foodymoody.be.comment.domain.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyReadService {

    private final ReplyRepository replyRepository;
    private final CommentMapper commentMapper;

    @Transactional(readOnly = true)
    public Slice<MemberReplySummaryResponse> fetchAllReply(CommentId commentId, Pageable pageable) {
        Slice<MemberReplySummary> memberReplySummaries = replyRepository.findByCommentId(commentId, pageable);
        return commentMapper.toReplySummaryResponse(memberReplySummaries);
    }

    @Transactional(readOnly = true)
    public Slice<MemberReplySummaryResponse> fetchAllReplyByMemberId(CommentId commentId, String memberId,
            Pageable pageable) {
        Slice<MemberReplySummary> memberReplySummaries = replyRepository.findByCommentIdAndMemberId(commentId, memberId,
                pageable);
        return commentMapper.toReplySummaryResponse(memberReplySummaries);
    }

    public void validate(ReplyId replyId) {
        replyRepository.existsById(replyId);
    }
}
