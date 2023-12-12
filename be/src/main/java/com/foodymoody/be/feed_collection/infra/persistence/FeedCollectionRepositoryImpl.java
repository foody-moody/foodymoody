package com.foodymoody.be.feed_collection.infra.persistence;

import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection.domain.FeedCollectionRepository;
import com.foodymoody.be.feed_collection.infra.persistence.jpa.FeedCollectionJpaRepository;
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
