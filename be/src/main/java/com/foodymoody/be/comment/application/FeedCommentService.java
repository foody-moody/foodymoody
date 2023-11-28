package com.foodymoody.be.comment.application;

import com.foodymoody.be.comment.application.dto.response.CommentResponse;
import com.foodymoody.be.comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCommentService {

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public Slice<CommentResponse> fetchComments(String feedId, Pageable pageable) {
        return commentRepository.findWithMemberAllByFeedId(feedId, pageable);
    }
}
