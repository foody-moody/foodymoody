package com.foodymoody.be.comment_heart.domain;

import com.foodymoody.be.comment.domain.entity.CommentId;
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
    private String memberId;

    public CommentHeart(CommentHeartId id, CommentId commentId, String memberId) {
        this.id = id;
        this.commentId = commentId;
        this.memberId = memberId;
    }
}
