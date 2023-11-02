package com.foodymoody.be.feed.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedReadResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private String location;
    @JsonProperty
    private LocalDateTime createdAt;
    @JsonProperty
    private LocalDateTime updatedAt;
    @JsonProperty
    private String review;
    @JsonProperty
    private List<String> storeMood;
    @JsonProperty
    private List<FeedImageMenuResponse> images;
    @JsonProperty
    private int likeCount;
    @JsonProperty
    private boolean isLiked;
    @JsonProperty
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

}
