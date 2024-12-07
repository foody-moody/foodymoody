package com.foodymoody.be.feed_collection_comment_like_count.infra.event;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentAddedEvent;
import com.foodymoody.be.feed_collection_comment_like_count.application.service.FeedCollectionCommentLikeCountWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentLikeCountEventHandler {

    private final FeedCollectionCommentLikeCountWriteService service;

    @Async
    @EventListener(FeedCollectionCommentAddedEvent.class)
    public void handle(FeedCollectionCommentAddedEvent event) {
        var id = IdFactory.createFeedCollectionCommentLikeCountId();
        var likeCount = FeedCollectionCommentLikeCountMapper.toLikeCount(event, id);
        service.save(likeCount);
    }

}
