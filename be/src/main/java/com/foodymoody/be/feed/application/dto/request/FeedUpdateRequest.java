package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.common.util.ids.StoreMoodId;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class FeedUpdateRequest {

    @NotNull(message = "가게 정보는 필수입니다.")
    private StoreId storeId;
    @NotBlank
    @Size(min = 1, max = 500, message = "가게 리뷰는 최소 1글자부터 최대 500글자까지여야 합니다.")
    private String review;
    @Size(min = 1, max = 3, message = "무드는 최소 1개부터 최대 3개까지입니다.")
    private List<StoreMoodId> storeMoodIds;
    @Size(min = 1, message = "메뉴는 1개 이상이어야 합니다.")
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
