package com.foodymoody.be.feed.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodymoody.be.common.util.ids.FeedId;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionReadFeedDetailsResponse {

    private int feedAllCount;
    private String feedThumbnailUrl;
    private String storeName;
    private FeedId feedId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
    private List<String> moodNames;
    @JsonProperty
    private boolean isLiked;
    private int likeCount;
    private Long feedCommentCount;

    @Builder
    public CollectionReadFeedDetailsResponse(int feedAllCount, String feedThumbnailUrl, String storeName, FeedId feedId,
                                             LocalDateTime createdAt, LocalDateTime updatedAt, String description,
                                             List<String> moodNames, boolean isLiked, int likeCount, Long feedCommentCount) {
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

    public int getFeedAllCount() {
        return feedAllCount;
    }

    public String getFeedThumbnailUrl() {
        return feedThumbnailUrl;
    }

    public String getStoreName() {
        return storeName;
    }

    public FeedId getFeedId() {
        return feedId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getMoodNames() {
        return moodNames;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public Long getFeedCommentCount() {
        return feedCommentCount;
    }

}
