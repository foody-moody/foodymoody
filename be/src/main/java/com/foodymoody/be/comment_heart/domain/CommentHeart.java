package com.foodymoody.be.comment_heart.domain;

import com.foodymoody.be.common.util.ids.CommentHeartId;
import com.foodymoody.be.common.util.ids.CommentId;
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
public class CommentHeart {

    @EmbeddedId
    private CommentHeartId id;
    @AttributeOverride(name = "value", column = @Column(name = "comment_id"))
    private CommentId commentId;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentHeart(CommentHeartId id, CommentId commentId, MemberId memberId, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.commentId = commentId;
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
