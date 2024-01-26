package com.foodymoody.be.feed_comment_like.application.usecase;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedCommentLikeAddedEvent implements NotificationEvent {

    private final FeedId feedId;
    private final FeedCommentId feedCommentId;
    private final Content commentContent;
    private final MemberId toMemberId;
    private final MemberId fromMemberId;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private FeedCommentLikeAddedEvent(
            FeedId feedId,
            FeedCommentId feedCommentId,
            Content commentContent,
            MemberId toMemberId,
            MemberId fromMemberId,
            NotificationType notificationType,
            LocalDateTime createdAt
    ) {
        this.feedId = feedId;
        this.feedCommentId = feedCommentId;
        this.commentContent = commentContent;
        this.toMemberId = toMemberId;
        this.fromMemberId = fromMemberId;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static FeedCommentLikeAddedEvent of(
            FeedId feedId,
            FeedCommentId feedCommentId,
            Content commentContent,
            MemberId toMemberId,
            MemberId fromMemberId,
            LocalDateTime createdAt
    ) {
        return new FeedCommentLikeAddedEvent(
                feedId,
                feedCommentId,
                commentContent,
                toMemberId,
                fromMemberId,
                NotificationType.FEED_COMMENT_LIKED_ADDED_EVENT,
                createdAt
        );
    }
}
