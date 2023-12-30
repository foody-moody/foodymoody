package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface FeedCollectionDao {

    List<FeedCollectionSummary> findAllSummary(Pageable pageable);

    List<FeedCollectionSummary> findAllSummary(MemberId memberId, Pageable pageable);

    Optional<FeedCollection> fetchById(FeedCollectionId feedCollectionId);
}
