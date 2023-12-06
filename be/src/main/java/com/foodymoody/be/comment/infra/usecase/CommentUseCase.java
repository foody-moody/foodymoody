package com.foodymoody.be.comment.infra.usecase;

import com.foodymoody.be.comment.application.CommentWriteService;
import com.foodymoody.be.comment.application.dto.request.RegisterCommentRequest;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentUseCase {

    private final FeedService feedService;
    private final CommentWriteService commentWriteService;

    public CommentId registerComment(RegisterCommentRequest request, String memberId) {
        feedService.validate(request.getFeedId());
        return commentWriteService.registerComment(request, memberId);
    }
}
