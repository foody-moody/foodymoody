package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.common.util.ids.StoreMoodId;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedRegisterRequest {

    private StoreId storeId;
    private String review;
    private List<StoreMoodId> storeMoodIds;
    private List<ImageMenuPair> images;

    @Builder
    public FeedRegisterRequest(StoreId storeId, String review, List<StoreMoodId> storeMoodIds,
                               List<ImageMenuPair> images) {
        this.storeId = storeId;
        this.review = review;
        this.storeMoodIds = storeMoodIds;
        this.images = images;
    }

    public StoreId getStoreId() {
        return storeId;
    }

    public void setStoreId(StoreId storeId) {
        this.storeId = storeId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public List<StoreMoodId> getStoreMoodIds() {
        return storeMoodIds;
    }

    public void setStoreMoodIds(List<StoreMoodId> storeMoodIds) {
        this.storeMoodIds = storeMoodIds;
    }

    public List<ImageMenuPair> getImages() {
        return images;
    }

    public void setImages(List<ImageMenuPair> images) {
        this.images = images;
    }

}
