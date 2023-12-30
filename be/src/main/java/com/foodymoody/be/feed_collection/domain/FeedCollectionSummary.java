package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class FeedCollectionSummary {

    private List<String> moods;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @QueryProjection
    public FeedCollectionSummary(
            FeedCollectionId id,
            String thumbnailUrl,
            MemberId authorId,
            String nickname,
            String tasteMoodName,
            String profileImageUrl,
            String title,
            String description,
            Long likeCount,
            int followerCount,
            int feedCount,
            int commentCount,
            boolean isLiked,
            List<String> moods,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.thumbnailUrl = thumbnailUrl;
        this.author = new AuthorSummaryResponse(authorId.getValue(), nickname, tasteMoodName, profileImageUrl);
        this.title = title;
        this.description = description;
        this.likeCount = likeCount;
        this.isLiked = isLiked;
        this.followerCount = followerCount;
        this.commentCount = commentCount;
        this.moods = moods;
        this.feedCount = feedCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
