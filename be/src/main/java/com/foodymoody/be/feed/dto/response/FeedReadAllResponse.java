package com.foodymoody.be.feed.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedReadAllResponse {

    private String id;
    private FeedMemberResponse member;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String location;
    private String review;
    private List<String> storeMood;
    private List<FeedImageMenuResponse> images;
    private int likeCount;
    @JsonProperty("isLiked")
    private boolean isLiked;
    private int commentCount;

    @Builder
    public FeedReadAllResponse(String id, FeedMemberResponse member, LocalDateTime createdAt, LocalDateTime updatedAt,
            String location, String review, List<String> storeMood, List<FeedImageMenuResponse> images, int likeCount,
            boolean isLiked, int commentCount) {
        this.id = id;
        this.member = member;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public FeedMemberResponse getMember() {
        return member;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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
