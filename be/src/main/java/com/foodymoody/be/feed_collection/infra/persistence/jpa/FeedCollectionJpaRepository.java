package com.foodymoody.be.feed_collection.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCollectionJpaRepository extends JpaRepository<FeedCollection, FeedCollectionId> {

    Optional<FeedCollection> findByIdAndIsDeleted(FeedCollectionId feedCollectionId, boolean isDeleted);
}
