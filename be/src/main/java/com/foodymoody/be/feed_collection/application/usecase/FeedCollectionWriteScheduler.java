package com.foodymoody.be.feed_collection.application.usecase;

import com.foodymoody.be.feed_collection.application.service.FeedCollectionWriteService;
import com.foodymoody.be.feed_collection_like_count.application.service.FeedCollectionLikeCountReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionWriteScheduler {

    private final FeedCollectionWriteService feedCollectionWriteService;
    private final FeedCollectionLikeCountReadService feedCollectionLikeCountReadService;

    @Scheduled(fixedRate = 1000)
    public void updateLikeCount() {
        feedCollectionWriteService.updateLikeCount(feedCollectionLikeCountReadService.getAll());
    }
}
