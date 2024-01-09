package com.foodymoody.be.feed_collection_comment_like_count.infra.event;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentAddedEvent;
import com.foodymoody.be.feed_collection_comment_like_count.application.FeedCollectionCommentLikeCountWriteService;
import com.foodymoody.be.feed_collection_comment_like_count.domain.FeedCollectionCommentLikeCount;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentLikeCountEventHandler {

    private final FeedCollectionCommentLikeCountWriteService service;

    @EventListener(FeedCollectionCommentAddedEvent.class)
    public void handle(FeedCollectionCommentAddedEvent event) {
        var id = IdFactory.createFeedCollectionCommentLikeCountId();
        var likeCount = new FeedCollectionCommentLikeCount(
                id, event.getFeedCollectionCommentId(), 0L, event.getCreatedAt());
        service.save(likeCount);
    }
}