package com.foodymoody.be.feed_comment_like_count.domain;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedCommentLikeCountId;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedCommentLikeCount {

    @EmbeddedId
    private FeedCommentLikeCountId id;

    @AttributeOverride(name = "value", column = @Column(name = "comment_id"))
    private FeedCommentId feedCommentId;
    private long count;

    public FeedCommentLikeCount(FeedCommentLikeCountId id, FeedCommentId feedCommentId, long count) {
        this.id = id;
        this.feedCommentId = feedCommentId;
        this.count = count;
    }

}
