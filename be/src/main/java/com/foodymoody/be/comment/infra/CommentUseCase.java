package com.foodymoody.be.comment.infra;

import com.foodymoody.be.comment.application.CommentService;
import com.foodymoody.be.comment.application.dto.request.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentUseCase {

    private final FeedService feedService;
    private final CommentService commentService;

    public CommentId registerComment(RegisterCommentRequest request, String memberId) {
        feedService.validate(request.getFeedId());
        return commentService.registerComment(request, memberId);
    }
}
