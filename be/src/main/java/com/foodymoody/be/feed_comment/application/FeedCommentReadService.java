package com.foodymoody.be.feed_comment.application;

import com.foodymoody.be.common.exception.FeedCommentNotFoundException;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.response.MemberCommentSummary;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_comment.domain.repository.CommentRepository;
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

    @Transactional(readOnly = true)
    public FeedComment findById(FeedCommentId id) {
        return commentRepository.findById(id)
                .orElseThrow(FeedCommentNotFoundException::new);
    }
}
