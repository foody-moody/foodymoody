package com.foodymoody.be.feed_collection.infra.persistence;

import com.foodymoody.be.feed_collection.domain.FeedCollectionDao;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import com.foodymoody.be.feed_collection.infra.persistence.jpa.FeedCollectionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionDaoImpl implements FeedCollectionDao {

    private final FeedCollectionJpaRepository repository;

    @Override
    public Slice<FeedCollectionSummary> findAllSummary(Pageable pageable) {
        return repository.findAllSummary(pageable);
    }
}
