package com.foodymoody.be.collection.infra.persistence;

import com.foodymoody.be.collection.domain.FeedCollection;
import com.foodymoody.be.collection.domain.FeedCollectionRepository;
import com.foodymoody.be.collection.infra.persistence.jpa.FeedCollectionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionRepositoryImpl implements FeedCollectionRepository {

    private final FeedCollectionJpaRepository feedCollectionJpaRepository;

    @Override
    public FeedCollection save(FeedCollection feedCollection) {
        return feedCollectionJpaRepository.save(feedCollection);
    }
}
