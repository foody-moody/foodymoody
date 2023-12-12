package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FeedCollectionDao {

    Slice<FeedCollectionSummary> findAllSummary(Pageable pageable);

    Optional<FeedCollection> fetchById(FeedCollectionId feedCollectionId);
}
