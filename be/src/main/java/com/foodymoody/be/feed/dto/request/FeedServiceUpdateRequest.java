package com.foodymoody.be.feed.dto.request;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedServiceUpdateRequest {

    private String memberId;
    private String location;
    private String review;
    private List<String> storeMood;
    private List<ImageMenuPair> images;

    @Builder
    public FeedServiceUpdateRequest(String memberId, String location, String review, List<String> storeMood,
            List<ImageMenuPair> images) {
        this.memberId = memberId;
        this.location = location;
        this.review = review;
        this.storeMood = storeMood;
        this.images = images;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getLocation() {
        return location;
    }

    public String getReview() {
        return review;
    }

    public List<String> getStoreMood() {
        return storeMood;
    }

    public List<ImageMenuPair> getImages() {
        return images;
    }

}
