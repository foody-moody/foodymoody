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
public class CollectionReadFeedDetailsResponse {

    @Getter
    private int feedAllCount;
    @Getter
    private String feedThumbnailUrl;
    @Getter
    private String storeName;
    @Getter
    private FeedId feedId;
    @Getter
    private LocalDateTime createdAt;
    @Getter
    private LocalDateTime updatedAt;
    @Getter
    private String description;
    @Getter
    private List<String> moodNames;
    @JsonProperty
    private boolean isLiked;
    @Getter
    private int likeCount;
    @Getter
    private Long feedCommentCount;

    @Builder
    public CollectionReadFeedDetailsResponse(int feedAllCount, String feedThumbnailUrl, String storeName, FeedId feedId,
                                             LocalDateTime createdAt, LocalDateTime updatedAt, String description,
                                             List<String> moodNames, boolean isLiked, int likeCount,
                                             Long feedCommentCount) {
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
