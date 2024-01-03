package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import java.util.Optional;

public interface FeedCollectionRepository {

    FeedCollection save(FeedCollection feedCollection);

    boolean existsById(FeedCollectionId feedCollectionId);

    Optional<FeedCollection> findById(FeedCollectionId feedCollectionId);
}
