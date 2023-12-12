package com.foodymoody.be.comment.infra.usecase;

import com.foodymoody.be.comment.application.CommentWriteService;
import com.foodymoody.be.comment.application.dto.request.RegisterCommentRequest;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.FeedReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentUseCase {

    private final FeedReadService feedReadService;
    private final CommentWriteService commentWriteService;

    public CommentId registerComment(RegisterCommentRequest request, String memberId) {
        feedReadService.validateId(request.getFeedId());
        MemberId memberWriterId = new MemberId(memberId);
        return commentWriteService.registerComment(request, memberWriterId);
    }
}
