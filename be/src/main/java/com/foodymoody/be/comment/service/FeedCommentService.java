package com.foodymoody.be.comment.service;

import com.foodymoody.be.comment.controller.dto.CommentResponse;
import com.foodymoody.be.comment.repository.CommentRepository;
import com.foodymoody.be.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCommentService {

    private final FeedService feedService;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public Slice<CommentResponse> fetchComments(String feedId, Pageable pageable) {
        feedService.validate(feedId);
        return commentRepository.findWithMemberAllByFeedId(feedId, pageable);
    }
}
