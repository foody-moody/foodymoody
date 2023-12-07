package com.foodymoody.be.comment.domain.entity;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;

public class CommentAddedEvent implements Event {

    private final FeedId feedId;
    private final String content;
    private final NotificationType notificationType;
    private final CommentId commentId;
    private final MemberId memberId;
    private final LocalDateTime createdAt;

    private CommentAddedEvent(
            FeedId feedId,
            String content,
            NotificationType notificationType,
            CommentId commentId,
            MemberId memberId,
            LocalDateTime createdAt
    ) {
        this.feedId = feedId;
        this.content = content;
        this.notificationType = notificationType;
        this.commentId = commentId;
        this.memberId = memberId;
        this.createdAt = createdAt;
    }

    public static CommentAddedEvent of(
            FeedId feedId,
            String content,
            CommentId commentId,
            MemberId memberId,
            LocalDateTime createdAt
    ) {
        return new CommentAddedEvent(
                feedId,
                content,
                NotificationType.COMMENT_ADDED_EVENT,
                commentId,
                memberId,
                createdAt
        );
    }

    public FeedId getFeedId() {
        return feedId;
    }

    public String getContent() {
        return content;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public CommentId getCommentId() {
        return commentId;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


}
