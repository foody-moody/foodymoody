package com.foodymoody.be.feed_collection.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FeedCollectionDao {

    Slice<FeedCollectionSummary> findAllSummary(Pageable pageable);
}
