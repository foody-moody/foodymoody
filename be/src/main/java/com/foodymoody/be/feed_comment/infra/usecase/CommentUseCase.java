package com.foodymoody.be.feed_comment.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed_comment.application.CommentWriteService;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterCommentData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentUseCase {

    private final FeedReadService feedReadService;
    private final CommentWriteService commentWriteService;

    public FeedCommentId registerComment(RegisterCommentData data) {
        feedReadService.validateId(data.getFeedId());
        return commentWriteService.register(data);
    }
}
