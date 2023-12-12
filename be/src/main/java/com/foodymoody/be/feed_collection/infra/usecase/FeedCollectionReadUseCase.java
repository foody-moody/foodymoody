package com.foodymoody.be.feed_collection.infra.usecase;

import com.foodymoody.be.feed_collection.application.FeedCollectionReadService;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReadUseCase {

    private final FeedCollectionReadService feedCollectionReadService;

    public Slice<FeedCollectionSummary> fetchCollection(Pageable pageable) {
        return feedCollectionReadService.fetchCollection(pageable);
    }
}
