package com.foodymoody.be.feed_reply_like_count.infra.event;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentReplyAddedEvent;
import com.foodymoody.be.feed_reply_like_count.application.service.FeedReplyLikeCountWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class FeedReplyLikeCountReplyEventHandler {

    private final FeedReplyLikeCountWriteService service;

    @Async
    @EventListener(FeedCommentReplyAddedEvent.class)
    public void handle(FeedCommentReplyAddedEvent event) {
        var feedReplyLikeCountId = IdFactory.createFeedReplyLikeCountId();
        var feedReplyLikeCount = FeedReplyLikeCountMapper.toFeedReplyLikeCount(event, feedReplyLikeCountId);
        service.save(feedReplyLikeCount);
    }

}
