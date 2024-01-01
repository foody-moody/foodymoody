package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionReadFeedListServiceRequest {

    private FeedCollectionId feedCollectionId;
    private Pageable pageable;
    private MemberId memberId;

    public CollectionReadFeedListServiceRequest(String feedCollectionId, Pageable pageable,
                                                MemberId memberId) {
        this.feedCollectionId = IdFactory.createFeedCollectionId(feedCollectionId);
        this.pageable = pageable;
        this.memberId = memberId;
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

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId memberId) {
        this.memberId = memberId;
    }

}
