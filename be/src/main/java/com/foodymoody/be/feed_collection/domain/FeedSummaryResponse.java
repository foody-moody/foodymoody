package com.foodymoody.be.feed_collection.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class FeedSummaryResponse {

    private String id;
    private String thumbnailUrl;
    private String content;
    private List<String> moods;
    private int likeCount;
    private int commentCount;
    private boolean isLiked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedSummaryResponse(
            String id, String thumbnailUrl, String content, List<String> moods, int likeCount,
            int commentCount, boolean isLiked, LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.thumbnailUrl = thumbnailUrl;
        this.content = content;
        this.moods = moods;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.isLiked = isLiked;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
