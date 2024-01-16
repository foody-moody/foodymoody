package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.common.util.ids.StoreMoodId;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FeedServiceUpdateRequest {

    private FeedId id;
    private MemberId memberId;
    private StoreId storeId;
    private String review;
    private List<StoreMoodId> storeMoodIds;
    private List<ImageMenuPair> images;

    @Builder
    public FeedServiceUpdateRequest(FeedId id, MemberId memberId, StoreId storeId, String review,
                                    List<StoreMoodId> storeMoodIds, List<ImageMenuPair> images) {
        this.id = id;
        this.memberId = memberId;
        this.storeId = storeId;
        this.review = review;
        this.storeMoodIds = storeMoodIds;
        this.images = images;
    }

}
