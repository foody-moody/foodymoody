package com.foodymoody.be.feed_collection_like.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
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
public class FeedCollectionLike {

    @Getter
    @Id
    private FeedCollectionLikeId id;
    @AttributeOverride(name = "value", column = @Column(name = "feed_collection_id"))
    private FeedCollectionId feedCollectionId;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    private LocalDateTime createdAt;

    public FeedCollectionLike(
            FeedCollectionLikeId id, FeedCollectionId feedCollectionId, MemberId memberId, LocalDateTime createdAt
    ) {
        this.id = id;
        this.feedCollectionId = feedCollectionId;
        this.memberId = memberId;
        this.createdAt = createdAt;
    }
}
