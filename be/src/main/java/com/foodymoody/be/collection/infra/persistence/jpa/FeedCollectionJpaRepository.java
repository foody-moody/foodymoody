package com.foodymoody.be.collection.infra.persistence.jpa;

import com.foodymoody.be.collection.domain.FeedCollection;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCollectionJpaRepository extends JpaRepository<FeedCollection, FeedCollectionId> {

}
