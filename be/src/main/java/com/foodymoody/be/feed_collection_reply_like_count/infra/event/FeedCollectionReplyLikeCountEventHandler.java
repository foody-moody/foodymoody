package com.foodymoody.be.feed_collection_reply_like_count.infra.event;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplyAddedEvent;
import com.foodymoody.be.feed_collection_reply_like_count.application.FeedCollectionReplyLikeCountWriteService;
import com.foodymoody.be.feed_collection_reply_like_count.domain.FeedCollectionReplyLikeCount;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyLikeCountEventHandler {

    private final FeedCollectionReplyLikeCountWriteService service;

    @EventListener(FeedCollectionReplyAddedEvent.class)
    public void handle(FeedCollectionReplyAddedEvent event) {
        var id = IdFactory.createFeedCollectionReplyLikeCountId();
        var likeCount = new FeedCollectionReplyLikeCount(
                id, event.getFeedCollectionReplyId(), 0L, LocalDateTime.now());
        service.save(likeCount);
    }
}
