package com.foodymoody.be.feed_collection.application;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMood;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMoodRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionMoodReadService {

    private final FeedCollectionMoodRepository repository;

    public Slice<FeedCollectionMood> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<FeedCollectionMood> findByFeedCollectionId(FeedCollectionId id) {
        return repository.findResponseByFeedCollectionId(id);
    }
}
