package com.foodymoody.be.comment.application;

import com.foodymoody.be.comment.application.dto.response.MemberCommentSummary;
import com.foodymoody.be.comment.domain.repository.CommentRepository;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
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
    public Slice<MemberCommentSummary> fetchComments(String feedId, Pageable pageable) {
        FeedId feedIdObj = IdFactory.createFeedId(feedId);
        return commentRepository.findWithMemberAllByFeedId(feedIdObj, pageable);
    }

    @Transactional(readOnly = true)
    public Slice<MemberCommentSummary> fetchComments(String feedId, Pageable pageable, String memberId) {
        FeedId feedIdObj = IdFactory.createFeedId(feedId);
        MemberId memberIdObj = IdFactory.createMemberId(memberId);
        return commentRepository.findWithMemberAllByFeedId(feedIdObj, memberIdObj, pageable);
    }
}
