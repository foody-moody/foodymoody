package com.foodymoody.be.feed_collection.application.service;

import com.foodymoody.be.common.exception.FeedCollectionNotFoundException;
import com.foodymoody.be.common.exception.PermissionDeniedAccessException;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection.domain.FeedCollectionDao;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionReadService {

    private final FeedCollectionDao dao;

    @Transactional(readOnly = true)
    public Slice<FeedCollectionSummary> fetchCollection(Pageable pageable) {
        return dao.findAllSummary(pageable);
    }

    @Transactional(readOnly = true)
    public Slice<FeedCollectionSummary> fetchCollection(MemberId memberId, Pageable pageable) {
        return dao.findAllSummary(memberId, pageable);
    }

    @Transactional(readOnly = true)
    public FeedCollection fetchById(FeedCollectionId feedCollectionId) {
        var feedCollection = dao.fetchById(feedCollectionId)
                .orElseThrow(FeedCollectionNotFoundException::new);
        if (feedCollection.isPrivate()) {
            throw new PermissionDeniedAccessException();
        }
        return feedCollection;
    }

    @Transactional(readOnly = true)
    public FeedCollection fetchById(FeedCollectionId id, MemberId memberId) {
        var feedCollection = dao.fetchById(id)
                .orElseThrow(FeedCollectionNotFoundException::new);
        if (feedCollection.isPrivate()) {
            feedCollection.validateAuthor(memberId);
        }
        return feedCollection;
    }
}
