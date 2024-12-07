package com.foodymoody.be.feed_comment.application.service;

import com.foodymoody.be.common.exception.FeedReplyNotFoundException;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.response.MemberFeedReplySummaryResponse;
import com.foodymoody.be.feed_comment.application.mapper.FeedCommentMapper;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import com.foodymoody.be.feed_comment.domain.repository.FeedReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedReplyReadService {

    private final FeedReplyRepository feedReplyRepository;

    @Transactional(readOnly = true)
    public Slice<MemberFeedReplySummaryResponse> fetchAllReply(FeedCommentId feedCommentId, Pageable pageable) {
        var memberReplySummaries = feedReplyRepository.findByCommentId(feedCommentId, pageable);
        return FeedCommentMapper.toReplySummaryResponses(memberReplySummaries);
    }

    @Transactional(readOnly = true)
    public Slice<MemberFeedReplySummaryResponse> fetchAllReplyByMemberId(
            FeedCommentId feedCommentId, MemberId memberId,
            Pageable pageable
    ) {
        var memberReplySummaries = feedReplyRepository.findByCommentIdAndMemberId(
                feedCommentId,
                memberId,
                pageable
        );
        return FeedCommentMapper.toReplySummaryResponses(memberReplySummaries);
    }

    @Transactional(readOnly = true)
    public void validate(FeedReplyId feedReplyId) {
        if (feedReplyRepository.existsById(feedReplyId)) {
            return;
        }
        throw new FeedReplyNotFoundException();
    }

    @Transactional(readOnly = true)
    public FeedReply fetchById(FeedReplyId feedReplyId) {
        return feedReplyRepository.findById(feedReplyId)
                .orElseThrow(FeedReplyNotFoundException::new);
    }

}
