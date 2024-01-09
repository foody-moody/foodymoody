package com.foodymoody.be.feed.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodymoody.be.common.util.ids.FeedId;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedReadResponse {

    private FeedId id;
    private FeedMemberResponse member;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String review;
    private List<FeedStoreMoodResponse> storeMood;
    private List<FeedImageMenuResponse> images;
    private int likeCount;
    @JsonProperty
    private boolean isLiked;
    private int commentCount;

    @Builder
    public FeedReadResponse(FeedId id, FeedMemberResponse member, String location, LocalDateTime createdAt,
                            LocalDateTime updatedAt, String review, List<FeedStoreMoodResponse> storeMood,
                            List<FeedImageMenuResponse> images, int likeCount, boolean isLiked, int commentCount) {
        this.id = id;
        this.member = member;
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

    public FeedId getId() {
        return id;
    }

    public FeedMemberResponse getMember() {
        return member;
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

    public List<FeedStoreMoodResponse> getStoreMood() {
        return storeMood;
    }

    public List<FeedImageMenuResponse> getImages() {
        return images;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

}
