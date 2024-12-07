package com.foodymoody.be.feed_collection.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMood;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedCollectionMoodJpaRepository extends JpaRepository<FeedCollectionMood, FeedCollectionMoodId> {

    @Query(
            "select _moods " +
                    "from FeedCollection _feedCollection " +
                    "join _feedCollection.moods.moodList _moods " +
                    "where _feedCollection.id = :id"
    )
    List<FeedCollectionMood> findResponseByFeedCollectionId(FeedCollectionId id);

}
