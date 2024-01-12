package com.foodymoody.be.comment.domain.entity;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentAddedEvent implements Event {

    private final FeedId feedId;
    private final Content content;
    private final NotificationType notificationType;
    private final CommentId commentId;
    private final MemberId memberId;
    private final LocalDateTime createdAt;

    private CommentAddedEvent(
            FeedId feedId,
            Content content,
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
            Content content,
            CommentId commentId,
            MemberId memberId,
            LocalDateTime createdAt
    ) {
        return new CommentAddedEvent(
                feedId,
                content,
                NotificationType.FEED_COMMENT_ADDED_EVENT,
                commentId,
                memberId,
                createdAt
        );
    }
}
