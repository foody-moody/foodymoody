package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MyFeedCollectionResponse {

    private FeedCollectionId id;
    private String title;
    private String thumbnailUrl;
    private int feedCount;
    private int likeCount;
    private int commentCount;
    private boolean liked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Setter
    private List<FeedCollectionMoodResponse> moods;

    public MyFeedCollectionResponse(
            FeedCollectionId id,
            String title,
            String thumbnailUrl,
            Integer feedCount,
            Integer likeCount,
            Integer commentCount,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Boolean liked
            ) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.feedCount = feedCount;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.liked = liked;
    }
}
