package com.foodymoody.be.feed_collection.application;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection.domain.FeedCollectionDao;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReadService {

    private final FeedCollectionDao dao;

    public List<FeedCollectionSummary> fetchCollection(Pageable pageable) {
        return dao.findAllSummary(pageable);
    }

    public List<FeedCollectionSummary> fetchCollection(MemberId memberId, Pageable pageable) {
        return dao.findAllSummary(memberId, pageable);
    }

    public FeedCollection fetch(FeedCollectionId feedCollectionId) {
        return dao.fetchById(feedCollectionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피드 컬렉션입니다."));
    }
}
