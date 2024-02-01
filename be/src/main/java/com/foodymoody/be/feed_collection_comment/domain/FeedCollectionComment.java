package com.foodymoody.be.feed_collection_comment.domain;

import com.foodymoody.be.common.event.EventManager;
import com.foodymoody.be.common.exception.PermissionDeniedAccessException;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
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
public class FeedCollectionComment {

    @Getter
    @Id
    private FeedCollectionCommentId id;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "feed_id"))
    private FeedCollectionId feedCollectionId;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "content"))
    private Content content;
    private boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private FeedCollectionReplyIds replyIds;

    public FeedCollectionComment(
            FeedCollectionCommentId id,
            FeedCollectionId feedCollectionId,
            MemberId memberId,
            Content content,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.feedCollectionId = feedCollectionId;
        this.memberId = memberId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        EventManager.raise(toFeedCollectionCommentAddedEvent(id, feedCollectionId, memberId, content, createdAt));
    }

    public void delete(MemberId memberId, LocalDateTime updatedAt) {
        if (memberId.equals(this.memberId)) {
            this.deleted = true;
            this.updatedAt = updatedAt;
            return;
        }
        throw new PermissionDeniedAccessException();
    }

    public void update(Content content, MemberId memberId, LocalDateTime updatedAt) {
        if (memberId.equals(this.memberId)) {
            this.content = content;
            this.updatedAt = updatedAt;
            return;
        }
        throw new PermissionDeniedAccessException();
    }

    public void addReplyIds(FeedCollectionReplyId id) {
        this.replyIds.add(id);
    }

    private static FeedCollectionCommentAddedEvent toFeedCollectionCommentAddedEvent(
            FeedCollectionCommentId id,
            FeedCollectionId feedCollectionId,
            MemberId memberId,
            Content content,
            LocalDateTime createdAt
    ) {
        return FeedCollectionCommentAddedEvent.of(
                feedCollectionId,
                memberId,
                id,
                content,
                createdAt
        );
    }
}
