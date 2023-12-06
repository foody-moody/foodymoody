package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedNotificationId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedNotification {

    @EmbeddedId
    private FeedNotificationId id;
    private String fromMemberId;
    private String toMemberId;
    private String message;
    @AttributeOverride(name = "value", column = @Column(name = "feed_id"))
    private FeedId feedId;
    @AttributeOverride(name = "value", column = @Column(name = "comment_id"))
    private CommentId commentId;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    private boolean isRead;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedNotification(FeedNotificationId id, String fromMemberId, String toMemberId, String message,
            FeedId feedId, CommentId commentId, NotificationType type, boolean isRead, boolean isDeleted,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.fromMemberId = fromMemberId;
        this.toMemberId = toMemberId;
        this.message = message;
        this.feedId = feedId;
        this.commentId = commentId;
        this.type = type;
        this.isRead = isRead;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public FeedNotificationId getId() {
        return id;
    }

    public String getFromMemberId() {
        return fromMemberId;
    }

    public String getToMemberId() {
        return toMemberId;
    }

    public String getMessage() {
        return message;
    }

    public FeedId getFeedId() {
        return feedId;
    }

    public CommentId getCommentId() {
        return commentId;
    }

    public NotificationType getType() {
        return type;
    }

    public boolean isRead() {
        return isRead;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void changeStatus(boolean isRead, String memberId, LocalDateTime updatedAt) {
        checkMemberId(memberId);
        this.updatedAt = updatedAt;
        this.isRead = isRead;
    }

    public void delete(String memberId, LocalDateTime updatedAt) {
        checkMemberId(memberId);
        this.isDeleted = true;
        this.updatedAt = updatedAt;
    }

    private void checkMemberId(String memberId) {
        if (!memberId.equals(toMemberId)) {
            throw new IllegalArgumentException("해당 알림을 수정할 수 없습니다.");
        }
    }
}
