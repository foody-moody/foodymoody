package com.foodymoody.be.feed_collection.application;

import com.foodymoody.be.feed_collection.domain.FeedCollectionDao;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReadService {

    private final FeedCollectionDao dao;

    public Slice<FeedCollectionSummary> fetchCollection(Pageable pageable) {
        return dao.findAllSummary(pageable);
    }
}
