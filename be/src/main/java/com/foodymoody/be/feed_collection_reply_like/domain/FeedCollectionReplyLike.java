package com.foodymoody.be.feed_collection_reply_like.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyLikeId;
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
public class FeedCollectionReplyLike {

    @Getter
    @Id
    private FeedCollectionReplyLikeId id;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    @AttributeOverride(name = "value", column = @Column(name = "feed_collection_id"))
    private FeedCollectionReplyId feedCollectionReplyId;
    private LocalDateTime createdAt;

    public FeedCollectionReplyLike(
            FeedCollectionReplyLikeId id,
            MemberId memberId,
            FeedCollectionReplyId feedCollectionReplyId,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.memberId = memberId;
        this.feedCollectionReplyId = feedCollectionReplyId;
        this.createdAt = createdAt;
    }
}
