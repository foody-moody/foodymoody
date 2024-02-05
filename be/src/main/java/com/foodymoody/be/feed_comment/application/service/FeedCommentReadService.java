package com.foodymoody.be.feed_comment.application.service;

import com.foodymoody.be.common.exception.FeedCommentNotFoundException;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_comment.domain.entity.MemberFeedCommentSummary;
import com.foodymoody.be.feed_comment.domain.repository.FeedCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCommentReadService {

    private final FeedCommentRepository feedCommentRepository;

    @Transactional(readOnly = true)
    public Slice<MemberFeedCommentSummary> fetchComments(FeedId feedId, Pageable pageable) {
        return feedCommentRepository.findWithMemberAllByFeedId(feedId, pageable);
    }

    @Transactional(readOnly = true)
    public Slice<MemberFeedCommentSummary> fetchComments(FeedId feedId, Pageable pageable, MemberId memberId) {
        return feedCommentRepository.findWithMemberAllByFeedId(feedId, memberId, pageable);
    }

    @Transactional(readOnly = true)
    public FeedComment findById(FeedCommentId id) {
        return feedCommentRepository.findById(id)
                .orElseThrow(FeedCommentNotFoundException::new);
    }
}
