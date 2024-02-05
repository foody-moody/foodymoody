package com.foodymoody.be.feed_collection_comment_like_count.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentLikeCountId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedCollectionCommentLikeCount {

    @Getter
    @Id
    private FeedCollectionCommentLikeCountId id;
    @AttributeOverride(name = "value", column = @Column(name = "feed_collection_comment_id"))
    private FeedCollectionCommentId feedCollectionCommentId;
    private Long count;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedCollectionCommentLikeCount(
            FeedCollectionCommentLikeCountId id, FeedCollectionCommentId feedCollectionCommentId, Long count,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.feedCollectionCommentId = feedCollectionCommentId;
        this.count = count;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public long getLikeCount() {
        return count;
    }
}
