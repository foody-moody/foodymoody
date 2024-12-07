package com.foodymoody.be.feed_collection_reply_like_count.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyLikeCountId;
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
public class FeedCollectionReplyLikeCount {

    @Getter
    @Id
    private FeedCollectionReplyLikeCountId id;

    @AttributeOverride(name = "value", column = @Column(name = "feed_collection_reply_id"))
    private FeedCollectionReplyId feedCollectionReplyId;
    private Long count;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedCollectionReplyLikeCount(
            FeedCollectionReplyLikeCountId id, FeedCollectionReplyId feedCollectionReplyId, Long count,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.feedCollectionReplyId = feedCollectionReplyId;
        this.count = count;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

}
