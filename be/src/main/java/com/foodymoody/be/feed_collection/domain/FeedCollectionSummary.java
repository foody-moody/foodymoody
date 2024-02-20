package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedCollectionSummary {

    private FeedCollectionId id;
    private AuthorSummaryResponse author;
    private String title;
    private String description;
    private int likeCount;
    private boolean isLiked;
    private String thumbnailUrl;
    private int followerCount;
    private int commentCount;
    private int feedCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @QueryProjection
    public FeedCollectionSummary(
            FeedCollectionId id,
            MemberId authorId,
            String nickname,
            String tasteMoodName,
            String profileImageUrl,
            String title,
            String thumbnailUrl,
            String description,
            int likeCount,
            int followerCount,
            int feedCount,
            int commentCount,
            boolean isLiked,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.author = new AuthorSummaryResponse(authorId, nickname, tasteMoodName, profileImageUrl);
        this.title = title;
        this.description = description;
        this.likeCount = likeCount;
        this.isLiked = isLiked;
        this.followerCount = followerCount;
        this.commentCount = commentCount;
        this.thumbnailUrl = thumbnailUrl;
        this.feedCount = feedCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
