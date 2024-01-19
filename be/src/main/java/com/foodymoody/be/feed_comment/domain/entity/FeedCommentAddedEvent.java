package com.foodymoody.be.feed_comment.domain.entity;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedCommentAddedEvent implements NotificationEvent {

    private final FeedId feedId;
    private final MemberId fromMemberId;
    private final FeedCommentId feedCommentId;
    private final Content commentContent;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private FeedCommentAddedEvent(
            FeedId feedId,
            MemberId fromMemberId,
            FeedCommentId feedCommentId,
            Content commentContent,
            NotificationType notificationType,
            LocalDateTime createdAt
    ) {
        this.feedId = feedId;
        this.fromMemberId = fromMemberId;
        this.feedCommentId = feedCommentId;
        this.commentContent = commentContent;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static FeedCommentAddedEvent of(
            FeedId feedId,
            MemberId fromMemberId,
            FeedCommentId feedCommentId,
            Content commentContent,
            LocalDateTime createdAt
    ) {
        return new FeedCommentAddedEvent(
                feedId,
                fromMemberId,
                feedCommentId,
                commentContent,
                NotificationType.FEED_COMMENT_ADDED_EVENT,
                createdAt
        );
    }
}
