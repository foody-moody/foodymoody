package com.foodymoody.be.feed_comment.application;

import com.foodymoody.be.common.exception.ReplyNotExistsException;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.response.MemberReplySummaryResponse;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import com.foodymoody.be.feed_comment.domain.repository.ReplyRepository;
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
    public Slice<MemberReplySummaryResponse> fetchAllReply(FeedCommentId feedCommentId, Pageable pageable) {
        var memberReplySummaries = replyRepository.findByCommentId(feedCommentId, pageable);
        return commentMapper.toReplySummaryResponse(memberReplySummaries);
    }

    @Transactional(readOnly = true)
    public Slice<MemberReplySummaryResponse> fetchAllReplyByMemberId(
            FeedCommentId feedCommentId, MemberId memberId,
            Pageable pageable) {
        var memberReplySummaries = replyRepository.findByCommentIdAndMemberId(
                feedCommentId,
                memberId,
                pageable
        );
        return commentMapper.toReplySummaryResponse(memberReplySummaries);
    }

    public void validate(FeedReplyId feedReplyId) {
        if (replyRepository.existsById(feedReplyId)) {
            return;
        }
        throw new ReplyNotExistsException();
    }

    @Transactional(readOnly = true)
    public FeedReply fetchById(FeedReplyId feedReplyId) {
        return replyRepository.findById(feedReplyId)
                .orElseThrow(ReplyNotExistsException::new);
    }
}
