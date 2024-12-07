package com.foodymoody.be.feed_collection_like_count.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.feed_collection_like_count.domain.FeedCollectionLikeCount;
import com.foodymoody.be.feed_collection_like_count.domain.FeedCollectionLikeCountRepository;
import com.foodymoody.be.feed_collection_like_count.infra.persistence.jpa.FeedCollectionLikeCountJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionLikeCountRepositoryImpl implements FeedCollectionLikeCountRepository {

    private final FeedCollectionLikeCountJpaRepository repository;

    @Override
    public void increaseCount(FeedCollectionId feedCollectionId) {
        repository.increaseCount(feedCollectionId);
    }

    @Override
    public void save(FeedCollectionLikeCount likeCount) {
        repository.save(likeCount);
    }

    @Override
    public void decreaseCount(FeedCollectionId feedCollectionId) {
        repository.decreaseCount(feedCollectionId);
    }

    @Override
    public Optional<FeedCollectionLikeCount> findByFeedCollectionId(FeedCollectionId feedCollectionId) {
        return repository.findByFeedCollectionId(feedCollectionId);
    }

    @Override
    public List<FeedCollectionLikeCount> findAll() {
        return repository.findAll();
    }

}
