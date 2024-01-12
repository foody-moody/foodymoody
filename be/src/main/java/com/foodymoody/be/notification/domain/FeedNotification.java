package com.foodymoody.be.notification.domain;

import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedNotification {

    @Getter
    @EmbeddedId
    private NotificationId id;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "from_member_id"))
    private MemberId fromMemberId;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "to_member_id"))
    private MemberId toMemberId;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "message"))
    private Content message;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "feed_id"))
    private FeedId feedId;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "comment_id"))
    private CommentId commentId;
    @Getter
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    @Getter
    private boolean isRead;
    @Getter
    private boolean isDeleted;
    @Getter
    private LocalDateTime createdAt;
    @Getter
    private LocalDateTime updatedAt;

    public FeedNotification(
            NotificationId id, MemberId fromMemberId, MemberId toMemberId, Content message, FeedId feedId,
            CommentId commentId, NotificationType type, boolean isRead, boolean isDeleted, LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
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

    public void changeStatus(boolean isRead, MemberId memberId, LocalDateTime updatedAt) {
        checkMemberId(memberId);
        this.updatedAt = updatedAt;
        this.isRead = isRead;
    }

    public void delete(MemberId memberId, LocalDateTime updatedAt) {
        checkMemberId(memberId);
        this.isDeleted = true;
        this.updatedAt = updatedAt;
    }

    public void checkMemberId(MemberId memberId) {
        if (!this.toMemberId.equals(memberId)) {
            throw new IllegalArgumentException("해당 알림을 수정할 수 없습니다.");
        }
    }
}
