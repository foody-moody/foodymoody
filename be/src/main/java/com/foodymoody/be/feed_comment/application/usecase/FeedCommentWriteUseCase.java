package com.foodymoody.be.feed_comment.application.usecase;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.feed.application.service.FeedReadService;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterFeedCommentData;
import com.foodymoody.be.feed_comment.application.service.FeedCommentWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedCommentWriteUseCase {

    private final FeedReadService feedService;
    private final FeedCommentWriteService feedCommentService;

    public FeedCommentId registerComment(RegisterFeedCommentData data) {
        feedService.validateId(data.getFeedId());
        return feedCommentService.register(data);
    }
}
