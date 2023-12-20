package com.foodymoody.be.feed.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodymoody.be.common.util.ids.FeedId;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionReadAllFeedResponse {

    @JsonProperty
    private int feedAllCount;
    @JsonProperty
    private String feedThumbnailUrl;
    @JsonProperty
    private String storeName;
    @JsonProperty
    private FeedId feedId;
    @JsonProperty
    private LocalDateTime createdAt;
    @JsonProperty
    private LocalDateTime updatedAt;
    @JsonProperty
    private String description;
    @JsonProperty
    private List<String> moodNames;
    @JsonProperty
    private boolean isLiked;
    @JsonProperty
    private int likeCount;
    @JsonProperty
    private int feedCommentCount;

    @Builder
    public CollectionReadAllFeedResponse(int feedAllCount, String feedThumbnailUrl, String storeName, FeedId feedId,
                                         LocalDateTime createdAt, LocalDateTime updatedAt, String description,
                                         List<String> moodNames, boolean isLiked, int likeCount, int feedCommentCount) {
        this.feedAllCount = feedAllCount;
        this.feedThumbnailUrl = feedThumbnailUrl;
        this.storeName = storeName;
        this.feedId = feedId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.description = description;
        this.moodNames = moodNames;
        this.isLiked = isLiked;
        this.likeCount = likeCount;
        this.feedCommentCount = feedCommentCount;
    }

}
