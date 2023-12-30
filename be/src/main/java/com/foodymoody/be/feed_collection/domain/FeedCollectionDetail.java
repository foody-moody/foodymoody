package com.foodymoody.be.feed_collection.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class FeedCollectionDetail {

    private String id;
    private String thumbnailUrl;
    private AuthorSummaryResponse author;
    private String title;
    private String description;
    private int likeCount;
    private boolean isLiked;
    private int followerCount;
    private boolean isPrivate;
    private int viewCount;
    private int commentCount;
    private int feedCount;
    private List<FeedSummaryResponse> feeds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedCollectionDetail(
            FeedCollection feedCollection, AuthorSummaryResponse author, List<FeedSummaryResponse> feeds
    ) {
        this.author = author;
        this.id = feedCollection.getId().getValue();
        this.thumbnailUrl = feedCollection.getThumbnailUrl();
        this.title = feedCollection.getTitle();
        this.description = feedCollection.getDescription();
        this.likeCount = feedCollection.getHeartCount();
        this.isLiked = false;
        this.followerCount = feedCollection.getFollowerCount();
        this.isPrivate = feedCollection.isPrivate();
        this.viewCount = 0;
        this.createdAt = feedCollection.getCreatedAt();
        this.updatedAt = feedCollection.getUpdatedAt();
        this.feeds = feeds;
        this.feedCount = feeds.size();
    }
}
