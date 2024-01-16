package com.foodymoody.be.comment.infra.usecase;

import com.foodymoody.be.comment.application.CommentWriteService;
import com.foodymoody.be.comment.application.dto.data.RegisterCommentData;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.feed.application.FeedReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentUseCase {

    private final FeedReadService feedReadService;
    private final CommentWriteService commentWriteService;

    public CommentId registerComment(RegisterCommentData data) {
        feedReadService.validateId(data.getFeedId());
        return commentWriteService.register(data);
    }
}
