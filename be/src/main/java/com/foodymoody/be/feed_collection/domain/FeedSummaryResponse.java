package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.feed.application.dto.response.StoreMoodResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class FeedSummaryResponse {

    private String id;
    private String thumbnailUrl;
    private String content;
    private List<StoreMoodResponse> storeMood;
    private int likeCount;
    private int commentCount;
    private boolean isLiked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedSummaryResponse(
            String id, String thumbnailUrl, String content, List<StoreMoodResponse> moods, int likeCount,
            int commentCount, boolean isLiked, LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.thumbnailUrl = thumbnailUrl;
        this.content = content;
        this.storeMood = moods;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.isLiked = isLiked;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
