package com.foodymoody.be.feed_collection.infra.usecase;

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
    private boolean isLiked;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedSummaryResponse(
            String id, String thumbnailUrl, String content, List<String> moods, int likeCount, boolean isLiked,
            int commentCount, LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.thumbnailUrl = thumbnailUrl;
        this.content = content;
        this.moods = moods;
        this.likeCount = likeCount;
        this.isLiked = isLiked;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
