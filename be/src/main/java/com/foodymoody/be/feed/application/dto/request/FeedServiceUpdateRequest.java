package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreMoodId;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedServiceUpdateRequest {

    private MemberId memberId;
    private String location;
    private String review;
    private List<StoreMoodId> storeMoodIds;
    private List<ImageMenuPair> images;

    @Builder
    public FeedServiceUpdateRequest(MemberId memberId, String location, String review, List<StoreMoodId> storeMoodIds,
                                    List<ImageMenuPair> images) {
        this.memberId = memberId;
        this.location = location;
        this.review = review;
        this.storeMoodIds = storeMoodIds;
        this.images = images;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public String getLocation() {
        return location;
    }

    public String getReview() {
        return review;
    }

    public List<StoreMoodId> getStoreMoodIds() {
        return storeMoodIds;
    }

    public List<ImageMenuPair> getImages() {
        return images;
    }

    public void setMemberId(MemberId memberId) {
        this.memberId = memberId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setStoreMoodIds(List<StoreMoodId> storeMoodIds) {
        this.storeMoodIds = storeMoodIds;
    }

    public void setImages(List<ImageMenuPair> images) {
        this.images = images;
    }

}
