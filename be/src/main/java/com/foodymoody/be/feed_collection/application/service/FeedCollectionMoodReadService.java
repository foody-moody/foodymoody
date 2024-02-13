package com.foodymoody.be.feed_collection.application.service;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMood;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMoodRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionMoodReadService {

    private final FeedCollectionMoodRepository repository;

    public List<FeedCollectionMood> findAll() {
        return repository.findAll();
    }

    public List<FeedCollectionMood> findByFeedCollectionId(FeedCollectionId id) {
        return repository.findResponseByFeedCollectionId(id);
    }
}
