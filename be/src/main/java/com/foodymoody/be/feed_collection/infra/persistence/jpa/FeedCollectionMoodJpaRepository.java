package com.foodymoody.be.feed_collection.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCollectionMoodJpaRepository extends JpaRepository<FeedCollectionMood, FeedCollectionMoodId> {

}
