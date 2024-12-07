package com.foodymoody.be.feed_comment_like_count.infra.event;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentAddedEvent;
import com.foodymoody.be.feed_comment_like_count.application.service.FeedCommentLikeCountWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class FeedCommentLikeCountCommentEventHandler {

    private final FeedCommentLikeCountWriteService feedCommentLikeCountService;

    @Async
    @EventListener(FeedCommentAddedEvent.class)
    public void handle(FeedCommentAddedEvent event) {
        var feedCommentLikeCountId = IdFactory.createFeedCommentLikeCountId();
        var feedCommentLikeCount = FeedCommentLikeCountMapper.toFeedCommentLikeCount(event, feedCommentLikeCountId);
        feedCommentLikeCountService.save(feedCommentLikeCount);
    }

}
