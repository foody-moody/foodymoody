package com.foodymoody.be.feed.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedReadAllResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private FeedMemberResponse member;
    @JsonProperty
    private LocalDateTime createdAt;
    @JsonProperty
    private LocalDateTime updatedAt;
    @JsonProperty
    private String location;
    @JsonProperty
    private String review;
    @JsonProperty
    private List<FeedStoreMoodResponse> storeMood;
    @JsonProperty
    private List<FeedImageMenuResponse> images;
    @JsonProperty
    private int likeCount;
    @JsonProperty
    private boolean isLiked;
    @JsonProperty
    private int commentCount;

    @Builder
    public FeedReadAllResponse(String id, FeedMemberResponse member, LocalDateTime createdAt, LocalDateTime updatedAt,
            String location, String review, List<FeedStoreMoodResponse> storeMood,
            List<FeedImageMenuResponse> images, int likeCount, boolean isLiked, int commentCount) {
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
}
