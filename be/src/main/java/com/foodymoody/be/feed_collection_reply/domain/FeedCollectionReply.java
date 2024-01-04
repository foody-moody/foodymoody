package com.foodymoody.be.feed_collection_reply.domain;

import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
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
public class FeedCollectionReply {

    @Getter
    @Id
    private FeedCollectionReplyId id;
    @AttributeOverride(name = "value", column = @Column(name = "comment_id"))
    private FeedCollectionCommentId commentId;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    private Content content;
    private boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedCollectionReply(
            FeedCollectionReplyId id,
            FeedCollectionCommentId commentId,
            MemberId memberId,
            Content content,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.commentId = commentId;
        this.memberId = memberId;
        this.content = content;
        this.deleted = false;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        Events.publish(FeedCollectionReplyAddedEvent.of(id));
    }

    public void delete(MemberId memberId, LocalDateTime updatedAt) {
        if (this.memberId.equals(memberId)) {
            this.deleted = true;
            this.updatedAt = updatedAt;
            return;
        }
        throw new IllegalArgumentException("삭제 권한이 없습니다.");
    }

    public void edit(Content content, MemberId memberId, LocalDateTime updatedAt) {
        if (this.memberId.equals(memberId)) {
            this.content = content;
            this.updatedAt = updatedAt;
            return;
        }
        throw new IllegalArgumentException("수정 권한이 없습니다.");
    }
}
