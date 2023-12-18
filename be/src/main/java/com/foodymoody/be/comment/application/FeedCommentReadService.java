package com.foodymoody.be.comment.application;

import com.foodymoody.be.comment.application.dto.response.MemberCommentSummary;
import com.foodymoody.be.comment.domain.repository.CommentRepository;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCommentReadService {

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public Slice<MemberCommentSummary> fetchComments(FeedId feedId, Pageable pageable) {
        return commentRepository.findWithMemberAllByFeedId(feedId, pageable);
    }

    @Transactional(readOnly = true)
    public Slice<MemberCommentSummary> fetchComments(FeedId feedId, Pageable pageable, MemberId memberId) {
        return commentRepository.findWithMemberAllByFeedId(feedId, memberId, pageable);
    }
}
