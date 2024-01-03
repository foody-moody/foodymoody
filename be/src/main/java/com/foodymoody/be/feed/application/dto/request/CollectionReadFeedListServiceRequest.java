package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.IdFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionReadFeedListServiceRequest {

    private FeedCollectionId feedCollectionId;
    private Pageable pageable;

    public CollectionReadFeedListServiceRequest(String collectionId, Pageable pageable) {
        this.feedCollectionId = IdFactory.createFeedCollectionId(collectionId);
        this.pageable = pageable;
    }

    public FeedCollectionId getFeedCollectionId() {
        return feedCollectionId;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setFeedCollectionId(FeedCollectionId feedCollectionId) {
        this.feedCollectionId = feedCollectionId;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

}
