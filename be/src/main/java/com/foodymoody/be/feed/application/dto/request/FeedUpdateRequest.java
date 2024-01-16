package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.common.util.ids.StoreMoodId;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedUpdateRequest {

    @Getter
    @Setter
    private StoreId storeId;

    @Getter
    @Setter
    private String review;

    @Getter
    @Setter
    private List<StoreMoodId> storeMoodIds;

    @Getter
    @Setter
    private List<ImageMenuPair> images;

    @Builder
    public FeedUpdateRequest(StoreId storeId, String review, List<StoreMoodId> storeMoodIds,
                             List<ImageMenuPair> images) {
        this.storeId = storeId;
        this.review = review;
        this.storeMoodIds = storeMoodIds;
        this.images = images;
    }

}
