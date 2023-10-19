package com.foodymoody.be.feed.dto.response;

import com.foodymoody.be.feed.domain.Feed;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedReadResponse {

    private Long id;
    private String location;
    // TODO: createdAt, updatedAt 추가 -> 테스트 코드 로직도 변경
    private String review;
    private String mood;
    private List<FeedImageMenuResponse> images;

    @Builder
    public FeedReadResponse(Long id, String location, String review, String mood, List<FeedImageMenuResponse> images) {
        this.id = id;
        this.location = location;
        this.review = review;
        this.mood = mood;
        this.images = images;
    }

    public Long getId() {
        return id;
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

    public List<FeedImageMenuResponse> getImages() {
        return images;
    }

}
