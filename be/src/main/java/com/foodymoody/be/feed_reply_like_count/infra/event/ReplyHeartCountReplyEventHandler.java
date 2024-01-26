package com.foodymoody.be.feed_reply_like_count.infra.event;

import com.foodymoody.be.common.util.ids.ReplyHeartCountId;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentReplyAddedEvent;
import com.foodymoody.be.feed_reply_like_count.application.service.ReplyHeartCountWriteService;
import com.foodymoody.be.feed_reply_like_count.domain.ReplyHeartCount;
import com.foodymoody.be.feed_reply_like_count.domain.ReplyHeartCountIdFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class ReplyHeartCountReplyEventHandler {

    private final ReplyHeartCountWriteService service;

    @Async
    @EventListener(FeedCommentReplyAddedEvent.class)
    public void handle(FeedCommentReplyAddedEvent event) {
        ReplyHeartCountId replyHeartCountId = ReplyHeartCountIdFactory.newId();
        ReplyHeartCount replyHeartCount = new ReplyHeartCount(replyHeartCountId, event.getFeedReplyId(), 0L);
        service.save(replyHeartCount);
    }
}
