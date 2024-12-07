package com.foodymoody.be.feed_collection.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection.domain.FeedCollectionRepository;
import com.foodymoody.be.feed_collection.infra.persistence.jpa.FeedCollectionJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionRepositoryImpl implements FeedCollectionRepository {

    private final FeedCollectionJpaRepository repository;

    @Override
    public FeedCollection save(FeedCollection feedCollection) {
        return repository.save(feedCollection);
    }

    @Override
    public boolean existsById(FeedCollectionId feedCollectionId) {
        return repository.existsById(feedCollectionId);
    }

    @Override
    public Optional<FeedCollection> findById(FeedCollectionId feedCollectionId) {
        return repository.findById(feedCollectionId);
    }

}
