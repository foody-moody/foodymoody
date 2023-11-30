package com.foodymoody.be.comment.application;

import com.foodymoody.be.comment.application.dto.response.MemberCommentSummaryResponse;
import com.foodymoody.be.comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCommentReadService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Transactional(readOnly = true)
    public Slice<MemberCommentSummaryResponse> fetchComments(String feedId, Pageable pageable) {
        return CommentMapper.mapToSummaryResponse(commentRepository.findWithMemberAllByFeedId(feedId, pageable));
    }
}