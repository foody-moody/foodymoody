package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import java.util.List;
import java.util.Optional;

public interface FeedCollectionMoodRepository {

    Optional<FeedCollectionMood> findById(FeedCollectionMoodId moodId);

    FeedCollectionMood save(FeedCollectionMood mood);

    List<FeedCollectionMood> findAll();

    List<FeedCollectionMood> findAllById(List<FeedCollectionMoodId> moodsIds);

    List<FeedCollectionMood> findResponseByFeedCollectionId(FeedCollectionId id);

}
