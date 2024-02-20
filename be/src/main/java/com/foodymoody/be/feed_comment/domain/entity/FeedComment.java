package com.foodymoody.be.feed_comment.domain.entity;

import static com.foodymoody.be.feed_comment.domain.entity.FeedCommentValidator.validate;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.event.EventManager;
import com.foodymoody.be.common.exception.CommentDeletedException;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
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
public class FeedComment {

    @Getter
    @Id
    private FeedCommentId id;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "feed_id"))
    private FeedId feedId;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "content"))
    private Content content;
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
    private FeedReplyComments feedReplyComments;

    public FeedComment(
            FeedCommentId id,
            Content content,
            FeedId feedId,
            boolean deleted,
            MemberId memberId,
            LocalDateTime createdAt
    ) {
        validate(id, content, feedId, memberId, createdAt);
        this.id = id;
        this.content = content;
        this.feedId = feedId;
        this.deleted = deleted;
        this.memberId = memberId;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.feedReplyComments = new FeedReplyComments();
        EventManager.raise(toCommentAddedEvent());
    }

    public void edit(MemberId memberId, Content content, LocalDateTime updatedAt) {
        if (!this.memberId.equals(memberId)) {
            throw new IllegalArgumentException();
        }
        if (this.deleted) {
            throw new CommentDeletedException();
        }
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

    public void addReply(FeedReply feedReply, LocalDateTime updatedAt) {
        this.feedReplyComments.add(feedReply);
        this.hasReply = true;
        this.updatedAt = updatedAt;
        EventManager.raise(toCommentRepliedAddedEvent(feedReply));
    }

    public void deleteReply(FeedReply feedReply, LocalDateTime updatedAt) {
        this.feedReplyComments.delete(feedReply);
        if (feedReplyComments.isEmpty()) {
            this.hasReply = false;
        }
        this.updatedAt = updatedAt;
    }

    private Event toCommentRepliedAddedEvent(FeedReply feedReply) {
        return FeedCommentReplyAddedEvent.of(
                id,
                memberId,
                feedId,
                feedReply.getId(),
                feedReply.getMemberId(),
                feedReply.getContent(),
                feedReply.getCreatedAt()
        );
    }

    private Event toCommentAddedEvent() {
        return FeedCommentAddedEvent.of(
                feedId,
                memberId,
                id,
                content,
                createdAt
        );
    }
}
