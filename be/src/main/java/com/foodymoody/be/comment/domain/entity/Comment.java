package com.foodymoody.be.comment.domain.entity;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.exception.CommentDeletedException;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

    @EmbeddedId
    private CommentId id;
    private String content;
    private String feedId;
    private boolean deleted;
    private String memberId;
    private boolean hasReply;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Embedded
    private ReplyComments replyComments;

    public Comment(CommentId id, String content, String feedId, boolean deleted, String memberId,
            LocalDateTime createdAt) {
        CommentValidator.validate(id, content, feedId, createdAt);
        this.id = id;
        this.content = content;
        this.feedId = feedId;
        this.deleted = deleted;
        this.memberId = memberId;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        Events.publish(toCommentAddedEvent());
    }

    public CommentId getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getFeedId() {
        return feedId;
    }

    public void edit(String memberId, String content, LocalDateTime updatedAt) {
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

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void delete(String memberId, LocalDateTime deletedAt) {
        if (!this.memberId.equals(memberId)) {
            throw new IllegalArgumentException();
        }
        if (this.deleted) {
            throw new CommentDeletedException();
        }
        this.deleted = true;
        this.updatedAt = deletedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getMemberId() {
        return memberId;
    }

    public List<Reply> getReplyComments() {
        return replyComments.getCommentList();
    }

    public void addReply(Reply reply) {
        this.replyComments.add(reply);
        this.hasReply = true;
        Events.publish(toCommentRepliedAddedEvent(reply));
    }

    private Event toCommentRepliedAddedEvent(Reply reply) {
        return CommentRepliedAddedEvent.of(id, memberId, reply.getMemberId(), reply.getContent(), reply.getCreatedAt());
    }

    private Event toCommentAddedEvent() {
        return CommentAddedEvent.of(feedId, content, id, memberId, createdAt);
    }
}
