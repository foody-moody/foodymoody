package com.foodymoody.be.feed_collection.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCollectionSampleJpaRepository extends JpaRepository<FeedCollectionSample, FeedCollectionId> {

    @EntityGraph(attributePaths = {"moods"})
    @Override
    Page<FeedCollectionSample> findAll(Pageable pageable);
}

