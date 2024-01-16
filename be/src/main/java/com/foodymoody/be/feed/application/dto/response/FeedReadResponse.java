package com.foodymoody.be.feed.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodymoody.be.common.util.ids.FeedId;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedReadResponse {

    @Getter
    private FeedId id;

    @Getter
    private FeedMemberResponse member;

    @Getter
    private String address;

    @Getter
    private LocalDateTime createdAt;

    @Getter
    private LocalDateTime updatedAt;

    @Getter
    private String review;

    @Getter
    private List<FeedStoreMoodResponse> storeMood;

    @Getter
    private List<FeedImageMenuResponse> images;

    @Getter
    private int likeCount;

    @JsonProperty
    private boolean isLiked;

    @Getter
    private Long commentCount;

    @Builder
    public FeedReadResponse(FeedId id, FeedMemberResponse member, String address, LocalDateTime createdAt,
                            LocalDateTime updatedAt, String review, List<FeedStoreMoodResponse> storeMood,
                            List<FeedImageMenuResponse> images, int likeCount, boolean isLiked, Long commentCount) {
        this.id = id;
        this.member = member;
        this.address = address;
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
