package com.foodymoody.be.feed.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedReadResponse {

    private String id;
    private String location;
    // TODO: createdAt, updatedAt 추가 -> 테스트 코드 로직도 변경
    private String review;
    private List<String> storeMood;
    private List<FeedImageMenuResponse> images;
    private int likeCount;
    @JsonProperty("isLiked")
    private boolean isLiked;
    private int commentCount;

    @Builder
    public FeedReadResponse(String id, String location, String review, List<String> storeMood,
            List<FeedImageMenuResponse> images,
            int likeCount, boolean isLiked, int commentCount) {
        this.id = id;
        this.location = location;
        this.review = review;
        this.storeMood = storeMood;
        this.images = images;
        this.likeCount = likeCount;
        this.isLiked = isLiked;
        this.commentCount = commentCount;
    }

    public String getId() {
        return id;
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

    public List<FeedImageMenuResponse> getImages() {
        return images;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public int getCommentCount() {
        return commentCount;
    }

}