package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.feed_collection.infra.usecase.dto.FeedCollectionCommentResponse;
import com.foodymoody.be.feed_collection.infra.usecase.dto.FeedCollectionMoodResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Slice;

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
    private int feedCount;
    private int commentCount;
    private List<FeedSummaryResponse> feeds;
    private Slice<FeedCollectionCommentResponse> comments;
    private List<FeedCollectionMoodResponse> moods;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedCollectionDetail(
            FeedCollection feedCollection,
            AuthorSummaryResponse author,
            List<FeedSummaryResponse> feeds,
            Slice<FeedCollectionCommentResponse> comments,
            List<FeedCollectionMoodResponse> moods
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
        this.comments = comments;
        commentCount = comments.getContent().size();
        this.moods = moods;
    }
}
