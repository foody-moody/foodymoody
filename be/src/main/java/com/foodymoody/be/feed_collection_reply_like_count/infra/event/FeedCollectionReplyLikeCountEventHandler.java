package com.foodymoody.be.feed_collection_reply_like_count.infra.event;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplyAddedEvent;
import com.foodymoody.be.feed_collection_reply_like_count.application.service.FeedCollectionReplyLikeCountWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyLikeCountEventHandler {

    private final FeedCollectionReplyLikeCountWriteService service;

    @Async
    @EventListener(FeedCollectionReplyAddedEvent.class)
    public void handle(FeedCollectionReplyAddedEvent event) {
        var id = IdFactory.createFeedCollectionReplyLikeCountId();
        var likeCount = FeedCollectionReplyLikeCountMapper.toLikeCount(event, id);
        service.save(likeCount);
    }

}
