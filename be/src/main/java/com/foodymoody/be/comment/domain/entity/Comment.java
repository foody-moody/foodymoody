package com.foodymoody.be.comment.domain.entity;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.exception.CommentDeletedException;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

    @Id
    private CommentId id;
    @AttributeOverride(name = "value", column = @Column(name = "feed_id"))
    private FeedId feedId;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    private String content;
    private boolean deleted;
    private boolean hasReply;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Embedded
    private ReplyComments replyComments;

    public Comment(
            CommentId id, String content, FeedId feedId, boolean deleted, MemberId memberId,
            LocalDateTime createdAt
    ) {
        CommentValidator.validate(id, content, feedId, createdAt);
        this.id = id;
        this.content = content;
        this.feedId = feedId;
        this.deleted = deleted;
        this.memberId = memberId;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        Events.raise(toCommentAddedEvent());
    }

    public CommentId getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public FeedId getFeedId() {
        return feedId;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void edit(MemberId memberId, String content, LocalDateTime updatedAt) {
        if (!this.memberId.equals(memberId)) {
            throw new IllegalArgumentException();
        }
        if (this.deleted) {
            throw new CommentDeletedException();
        }
        CommentValidator.validateContent(content);
        this.content = content;
        this.updatedAt = updatedAt;
    }

    public void delete(MemberId memberId, LocalDateTime deletedAt) {
        if (!this.memberId.equals(memberId)) {
            throw new IllegalArgumentException();
        }
        if (this.deleted) {
            throw new CommentDeletedException();
        }
        this.deleted = true;
        this.updatedAt = deletedAt;
    }

    public List<Reply> getReplyComments() {
        return replyComments.getCommentList();
    }

    public void addReply(Reply reply) {
        this.replyComments.add(reply);
        this.hasReply = true;
        Events.raise(toCommentRepliedAddedEvent(reply));
    }

    private Event toCommentRepliedAddedEvent(Reply reply) {
        return CommentRepliedAddedEvent.of(id, reply.getId(), memberId, reply.getMemberId(), reply.getContent(), feedId,
                                           reply.getCreatedAt()
        );
    }

    private Event toCommentAddedEvent() {
        return CommentAddedEvent.of(feedId, content, id, memberId, createdAt);
    }
}
