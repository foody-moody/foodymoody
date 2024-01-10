package com.foodymoody.be.comment.domain.entity;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.exception.CommentDeletedException;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

    @Getter
    @Id
    private CommentId id;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "feed_id"))
    private FeedId feedId;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    @Getter
    private String content;
    @Getter
    private boolean deleted;
    @Getter
    private boolean hasReply;
    @Getter
    private LocalDateTime createdAt;
    @Getter
    private LocalDateTime updatedAt;
    @Getter
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
        this.replyComments = new ReplyComments();
        Events.raise(toCommentAddedEvent());
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

    public void addReply(Reply reply, LocalDateTime updatedAt) {
        this.replyComments.add(reply);
        this.hasReply = true;
        this.updatedAt = updatedAt;
        Events.raise(toCommentRepliedAddedEvent(reply));
    }

    public void deleteReply(Reply reply, LocalDateTime updatedAt) {
        this.replyComments.delete(reply);
        this.updatedAt = updatedAt;
    }

    private Event toCommentRepliedAddedEvent(Reply reply) {
        return CommentRepliedAddedEvent.of(
                id,
                reply.getId(),
                memberId,
                reply.getMemberId(),
                reply.getContent(),
                feedId,
                reply.getCreatedAt()
        );
    }

    private Event toCommentAddedEvent() {
        return CommentAddedEvent.of(
                feedId,
                content,
                id,
                memberId,
                createdAt
        );
    }
}
