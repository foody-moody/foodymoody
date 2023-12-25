package com.foodymoody.be.feed_collection_like_count.infra.event;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection.domain.FeedCollectionAddedEvent;
import com.foodymoody.be.feed_collection_like_count.application.FeedCollectionLikeCountWriteService;
import com.foodymoody.be.feed_collection_like_count.domain.FeedCollectionLikeCount;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionLikeCountEventHandler {

    private final FeedCollectionLikeCountWriteService service;

    @EventListener(FeedCollectionAddedEvent.class)
    public void handle(FeedCollectionAddedEvent event) {
        var id = IdFactory.createFeedCollectionLikeCountId();
        var likeCount = new FeedCollectionLikeCount(
                id, event.getFeedCollectionId(), 0L, LocalDateTime.now());
        service.save(likeCount);
    }
}
