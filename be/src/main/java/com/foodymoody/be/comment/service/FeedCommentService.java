package com.foodymoody.be.comment.service;

import com.foodymoody.be.comment.domain.Comment;
import com.foodymoody.be.comment.repository.CommentRepository;
import com.foodymoody.be.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCommentService {

    private final FeedService feedService;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public Slice<Comment> fetchComments(String feedId) {
        feedService.validate(feedId);
        return commentRepository.findAllByFeedId(feedId);
    }
}
