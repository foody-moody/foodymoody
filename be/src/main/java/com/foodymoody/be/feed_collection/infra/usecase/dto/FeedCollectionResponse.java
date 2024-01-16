package com.foodymoody.be.feed_collection.infra.usecase.dto;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.feed_collection.domain.AuthorSummaryResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class FeedCollectionResponse {

    private FeedCollectionId id;
    private AuthorSummaryResponse author;
    private String title;
    private String description;
    private Long likeCount;
    private boolean isLiked;
    private int followerCount;
    private int commentCount;
    private String thumbnailUrl;
    private int feedCount;
    private List<FeedCollectionMoodResponse> storeMood;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedCollectionResponse(
            FeedCollectionId id, AuthorSummaryResponse author, String title, String description, String thumbnailUrl,
            Long likeCount, boolean isLiked, int followerCount, int commentCount, int feedCount,
            List<FeedCollectionMoodResponse> moods, LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.likeCount = likeCount;
        this.isLiked = isLiked;
        this.followerCount = followerCount;
        this.commentCount = commentCount;
        this.thumbnailUrl = thumbnailUrl;
        this.feedCount = feedCount;
        this.storeMood = moods;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
