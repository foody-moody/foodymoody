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
public class FeedServiceUpdateRequest {

    @Getter
    private FeedId id;

    @Getter
    private MemberId memberId;

    @Getter
    private StoreId storeId;

    @Getter
    private String review;

    @Getter
    private List<StoreMoodId> storeMoodIds;

    @Getter
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
