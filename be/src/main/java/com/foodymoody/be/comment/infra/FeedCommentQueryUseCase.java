package com.foodymoody.be.comment.infra;

import com.foodymoody.be.comment.application.FeedCommentService;
import com.foodymoody.be.comment.application.dto.response.CommentResponse;
import com.foodymoody.be.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedCommentQueryUseCase {

    private final FeedCommentService feedCommentService;
    private final FeedService feedService;

    public Slice<CommentResponse> fetchComments(String feedId, Pageable pageable) {
        feedService.validate(feedId);
        return feedCommentService.fetchComments(feedId, pageable);
    }
}
