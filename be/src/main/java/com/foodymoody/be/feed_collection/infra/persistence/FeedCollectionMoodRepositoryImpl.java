package com.foodymoody.be.feed_collection.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMood;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMoodRepository;
import com.foodymoody.be.feed_collection.infra.persistence.jpa.FeedCollectionMoodJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionMoodRepositoryImpl implements FeedCollectionMoodRepository {

    private final FeedCollectionMoodJpaRepository repository;

    @Override
    public Optional<FeedCollectionMood> findById(FeedCollectionMoodId moodId) {
        return repository.findById(moodId);
    }

    @Override
    public FeedCollectionMood save(FeedCollectionMood mood) {
        return repository.save(mood);
    }

    @Override
    public List<FeedCollectionMood> findAll() {
        return repository.findAll();
    }

    @Override
    public List<FeedCollectionMood> findAllById(List<FeedCollectionMoodId> moodsIds) {
        return repository.findAllById(moodsIds);
    }

    @Override
    public List<FeedCollectionMood> findResponseByFeedCollectionId(FeedCollectionId id) {
        return repository.findResponseByFeedCollectionId(id);
    }

}
