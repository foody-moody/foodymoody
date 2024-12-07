package com.foodymoody.be.feed_collection_like_count.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionLikeCountId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "feed_collection_like_count",
        indexes = {
                @Index(name = "idx_feed_collection_like_count_on_feed_collection_id", columnList = "feed_collection_id")
        }
)
public class FeedCollectionLikeCount {

    @Getter
    @Id
    private FeedCollectionLikeCountId id;

    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "feed_collection_id"))
    private FeedCollectionId feedCollectionId;

    @Getter
    private Long count;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedCollectionLikeCount(
            FeedCollectionLikeCountId id, FeedCollectionId feedCollectionId, Long count, LocalDateTime createdAt
    ) {
        this.id = id;
        this.feedCollectionId = feedCollectionId;
        this.count = count;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public long getLikeCount() {
        return count;
    }

}
