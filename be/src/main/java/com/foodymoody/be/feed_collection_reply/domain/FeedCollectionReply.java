package com.foodymoody.be.feed_collection_reply.domain;

import com.foodymoody.be.common.util.CommentContent;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedCollectionReply {

    @Id
    private FeedCollectionReplyId id;
    @AttributeOverride(name = "value", column = @Column(name = "comment_id"))
    private FeedCollectionCommentId commentId;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    private CommentContent content;
    private boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedCollectionReply(
            FeedCollectionReplyId id,
            FeedCollectionCommentId commentId,
            MemberId memberId,
            CommentContent content,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.commentId = commentId;
        this.memberId = memberId;
        this.content = content;
        this.deleted = false;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public FeedCollectionReplyId getId() {
        return id;
    }
}
