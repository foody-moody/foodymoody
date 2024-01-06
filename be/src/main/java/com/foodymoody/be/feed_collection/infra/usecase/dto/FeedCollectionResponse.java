package com.foodymoody.be.feed_collection.infra.usecase.dto;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.feed_collection.domain.AuthorSummaryResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class FeedCollectionResponse {

    private FeedCollectionId id;
    private String thumbnailUrl;
    private AuthorSummaryResponse author;
    private String title;
    private String description;
    private Long likeCount;
    private boolean isLiked;
    private int followerCount;
    private int commentCount;
    private int feedCount;
    private List<FeedCollectionMoodResponse> storeMood;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedCollectionResponse(
            FeedCollectionId id, String thumbnailUrl, AuthorSummaryResponse author, String title, String description,
            Long likeCount, boolean isLiked, int followerCount, int commentCount, int feedCount,
            List<FeedCollectionMoodResponse> moods, LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.thumbnailUrl = thumbnailUrl;
        this.author = author;
        this.title = title;
        this.description = description;
        this.likeCount = likeCount;
        this.isLiked = isLiked;
        this.followerCount = followerCount;
        this.commentCount = commentCount;
        this.feedCount = feedCount;
        this.storeMood = moods;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
