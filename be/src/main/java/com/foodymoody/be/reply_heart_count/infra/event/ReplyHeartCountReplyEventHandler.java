package com.foodymoody.be.reply_heart_count.infra.event;

import com.foodymoody.be.comment.domain.entity.CommentRepliedAddedEvent;
import com.foodymoody.be.common.util.ids.ReplyHeartCountId;
import com.foodymoody.be.reply_heart_count.application.ReplyHeartCountWriteService;
import com.foodymoody.be.reply_heart_count.domain.ReplyHeartCount;
import com.foodymoody.be.reply_heart_count.domain.ReplyHeartCountIdFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class ReplyHeartCountReplyEventHandler {

    private final ReplyHeartCountWriteService service;

    @EventListener(CommentRepliedAddedEvent.class)
    public void handle(CommentRepliedAddedEvent event) {
        ReplyHeartCountId replyHeartCountId = ReplyHeartCountIdFactory.newId();
        ReplyHeartCount replyHeartCount = new ReplyHeartCount(replyHeartCountId, event.getReplyId(), 0L);
        service.save(replyHeartCount);
    }
}
