package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.common.util.ids.StoreMoodId;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FeedServiceRegisterRequest {

    private MemberId memberId;
    private StoreId storeId;
    private String review;
    private List<StoreMoodId> storeMoodIds;
    private List<ImageMenuPair> images;

    @Builder
    public FeedServiceRegisterRequest(MemberId memberId, StoreId storeId, String review, List<StoreMoodId> storeMoodIds,
                                      List<ImageMenuPair> images) {
        this.memberId = memberId;
        this.storeId = storeId;
        this.review = review;
        this.storeMoodIds = storeMoodIds;
        this.images = images;
    }

}
