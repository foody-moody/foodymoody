package com.foodymoody.be.feed_comment_like_count.infra.event;

import com.foodymoody.be.common.util.ids.FeedCommentLikeCountId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentAddedEvent;
import com.foodymoody.be.feed_comment_like_count.application.service.FeedCommentLikeCountWriteService;
import com.foodymoody.be.feed_comment_like_count.domain.FeedCommentLikeCount;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CommentHeartCountCommentEventHandler {

    private final FeedCommentLikeCountWriteService feedCommentLikeCountWriteService;

    @Async
    @EventListener(FeedCommentAddedEvent.class)
    public void handle(FeedCommentAddedEvent event) {
        FeedCommentLikeCountId feedCommentLikeCountId = IdFactory.createFeedCommentLikeCountId();
        FeedCommentLikeCount feedCommentLikeCount = new FeedCommentLikeCount(
                feedCommentLikeCountId, event.getFeedCommentId(), 0L);
        feedCommentLikeCountWriteService.save(feedCommentLikeCount);
    }
}
