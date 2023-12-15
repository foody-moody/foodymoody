package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.IdFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionServiceReadAllFeedRequest {

    private FeedCollectionId feedCollectionId;
    private Pageable pageable;

    public CollectionServiceReadAllFeedRequest(String collectionId, Pageable pageable) {
        this.feedCollectionId = IdFactory.createFeedCollectionId(collectionId);
        this.pageable = pageable;
    }

    public FeedCollectionId getFeedCollectionId() {
        return feedCollectionId;
    }

    public Pageable getPageable() {
        return pageable;
    }

}
