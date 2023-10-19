package com.foodymoody.be.feed.dto.request;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedServiceRegisterRequest {

    private String location;
    private String review;
    private String mood;
    private List<ImageMenuPair> images;

    @Builder
    public FeedServiceRegisterRequest(String location, String review, String mood, List<ImageMenuPair> images) {
        this.location = location;
        this.review = review;
        this.mood = mood;
        this.images = images;
    }

    public String getLocation() {
        return location;
    }

    public String getReview() {
        return review;
    }

    public String getMood() {
        return mood;
    }

    public List<ImageMenuPair> getImages() {
        return images;
    }

}
