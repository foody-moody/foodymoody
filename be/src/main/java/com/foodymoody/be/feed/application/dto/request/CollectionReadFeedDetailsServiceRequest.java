package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionReadFeedDetailsServiceRequest {

    private FeedCollectionId collectionId;
    private Pageable pageable;
    private MemberId memberId;

    public CollectionReadFeedDetailsServiceRequest(FeedCollectionId collectionId, Pageable pageable,
                                                   MemberId memberId) {
        this.collectionId = collectionId;
        this.pageable = pageable;
        this.memberId = memberId;
    }

    public FeedCollectionId getFeedCollectionId() {
        return collectionId;
    }

    public void setFeedCollectionId(FeedCollectionId feedCollectionId) {
        this.collectionId = feedCollectionId;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId memberId) {
        this.memberId = memberId;
    }

}
