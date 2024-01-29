package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CollectionReadFeedDetailsServiceRequest {

    private FeedCollectionId collectionId;
    private Pageable pageable;
    private MemberId memberId;

    public CollectionReadFeedDetailsServiceRequest(
            FeedCollectionId collectionId,
            Pageable pageable,
            MemberId memberId
    ) {
        this.collectionId = collectionId;
        this.pageable = pageable;
        this.memberId = memberId;
    }

}
