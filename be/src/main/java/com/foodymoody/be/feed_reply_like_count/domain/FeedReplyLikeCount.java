package com.foodymoody.be.feed_reply_like_count.domain;

import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.FeedReplyLikeCountId;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedReplyLikeCount {

    @EmbeddedId
    private FeedReplyLikeCountId id;
    @AttributeOverride(name = "value", column = @Column(name = "reply_id"))
    private FeedReplyId feedReplyId;
    private long count;

    public FeedReplyLikeCount(FeedReplyLikeCountId id, FeedReplyId feedReplyId, long count) {
        this.id = id;
        this.feedReplyId = feedReplyId;
        this.count = count;
    }
}
