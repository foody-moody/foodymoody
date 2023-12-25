package com.foodymoody.be.feed_collection_like_count.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionLikeCountId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedCollectionLikeCount {

    @Getter
    @Id
    private FeedCollectionLikeCountId id;
    @AttributeOverride(name = "value", column = @javax.persistence.Column(name = "feed_collection_id"))
    private FeedCollectionId feedCollectionId;
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
