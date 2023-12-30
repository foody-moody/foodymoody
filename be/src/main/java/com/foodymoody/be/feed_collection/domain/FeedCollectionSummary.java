package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.infra.usecase.AuthorSummaryResponse;
import com.foodymoody.be.feed_collection.infra.usecase.FeedSummaryResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class FeedCollectionSummary {

    List<String> moods;
    private FeedCollectionId id;
    private String thumbnailUrl;
    private AuthorSummaryResponse author;
    private String title;
    private String description;
    private Long likeCount;
    private boolean isLiked;
    private int followerCount;
    private boolean isPrivate;
    private int viewCount;
    private int commentCount;
    private int feedCount;
    private List<FeedSummaryResponse> feeds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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
            boolean isPrivate,
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
        this.isPrivate = isPrivate;
        this.commentCount = commentCount;
        this.moods = moods;
        this.feedCount = feedCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
