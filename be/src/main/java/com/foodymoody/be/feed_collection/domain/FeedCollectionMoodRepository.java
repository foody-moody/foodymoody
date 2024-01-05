package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FeedCollectionMoodRepository {

    Optional<FeedCollectionMood> findById(FeedCollectionMoodId moodId);

    FeedCollectionMood save(FeedCollectionMood mood);

    Slice<FeedCollectionMood> findAll(Pageable pageable);

    List<FeedCollectionMood> findAllById(List<FeedCollectionMoodId> moodsIds);

    List<FeedCollectionMood> findResponseByFeedCollectionId(FeedCollectionId id);
}
