package com.foodymoody.be.feed_collection_like_count.application.service;

import com.foodymoody.be.feed_collection_like_count.domain.FeedCollectionLikeCount;
import com.foodymoody.be.feed_collection_like_count.domain.FeedCollectionLikeCountRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionLikeCountReadService {

    private final FeedCollectionLikeCountRepository repository;

    @Transactional(readOnly = true)
    public List<FeedCollectionLikeCount> getAll() {
        return repository.findAll();
    }
}
