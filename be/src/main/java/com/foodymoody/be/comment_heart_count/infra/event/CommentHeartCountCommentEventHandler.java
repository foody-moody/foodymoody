package com.foodymoody.be.comment_heart_count.infra.event;

import com.foodymoody.be.comment.domain.entity.CommentAddedEvent;
import com.foodymoody.be.comment_heart_count.application.CommentHeartCountWriteService;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCount;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCountIdFactory;
import com.foodymoody.be.common.util.ids.CommentHeartCountId;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CommentHeartCountCommentEventHandler {

    private final CommentHeartCountWriteService commentHeartCountWriteService;

    @EventListener(CommentAddedEvent.class)
    public void handle(CommentAddedEvent event) {
        CommentHeartCountId commentHeartCountId = CommentHeartCountIdFactory.newId();
        CommentHeartCount commentHeartCount = new CommentHeartCount(commentHeartCountId, event.getCommentId(), 0L);
        commentHeartCountWriteService.save(commentHeartCount);
    }
}
