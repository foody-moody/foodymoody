package com.foodymoody.be.feed_comment_like.domain;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedCommentLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedCommentLike {

    @EmbeddedId
    private FeedCommentLikeId id;
    @AttributeOverride(name = "value", column = @Column(name = "comment_id"))
    private FeedCommentId feedCommentId;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedCommentLike(
            FeedCommentLikeId id, FeedCommentId feedCommentId, MemberId memberId, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.feedCommentId = feedCommentId;
        this.memberId = memberId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public MemberId getMemberId() {
        return memberId;
    }
}
