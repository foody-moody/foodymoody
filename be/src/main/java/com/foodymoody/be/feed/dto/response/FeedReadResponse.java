package com.foodymoody.be.feed.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedReadResponse {

    private String id;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String review;
    private List<String> storeMood;
    private List<FeedImageMenuResponse> images;
    private int likeCount;
    @JsonProperty("isLiked")
    private boolean isLiked;
    private int commentCount;

    @Builder
    public FeedReadResponse(String id, String location, LocalDateTime createdAt, LocalDateTime updatedAt, String review,
            List<String> storeMood, List<FeedImageMenuResponse> images, int likeCount, boolean isLiked,
            int commentCount) {
        this.id = id;
        this.location = location;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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
