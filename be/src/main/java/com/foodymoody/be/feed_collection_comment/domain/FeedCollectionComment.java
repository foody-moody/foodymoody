package com.foodymoody.be.feed_collection_comment.domain;

import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
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
public class FeedCollectionComment {

    @Getter
    @Id
    private FeedCollectionCommentId id;
    @AttributeOverride(name = "value", column = @Column(name = "feed_id"))
    private FeedCollectionId feedId;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    @AttributeOverride(name = "value", column = @Column(name = "content"))
    private Content content;
    private boolean deleted;
    private boolean hasReply;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private FeedCollectionReplyIds replyIds;

    public FeedCollectionComment(
            FeedCollectionCommentId id, FeedCollectionId feedId, MemberId memberId, Content content,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.feedId = feedId;
        this.memberId = memberId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        Events.publish(FeedCollectionCommentAddedEvent.of(id, createdAt));
    }

    public void delete(MemberId memberId, LocalDateTime updatedAt) {
        if (memberId.equals(this.memberId)) {
            this.deleted = true;
            this.updatedAt = updatedAt;
            return;
        }
        throw new IllegalArgumentException("삭제 권한이 없습니다.");
    }

    public void update(Content content, MemberId memberId, LocalDateTime updatedAt) {
        if (memberId.equals(this.memberId)) {
            this.content = content;
            this.updatedAt = updatedAt;
            return;
        }
        throw new IllegalArgumentException("수정 권한이 없습니다.");
    }
}
