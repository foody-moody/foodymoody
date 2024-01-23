package com.foodymoody.be.feed_comment.domain.entity;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedCommentReplyAddedEvent implements NotificationEvent {

    private final MemberId fromMemberId;
    private final FeedCommentId feedCommentId;
    private final Content commentContent;
    private final FeedReplyId feedReplyId;
    private final FeedId feedId;
    private final MemberId toMemberId;
    private final Content replyContent;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private FeedCommentReplyAddedEvent(
            FeedCommentId feedCommentId,
            Content commentContent,
            MemberId toMemberId,
            FeedId feedId,
            FeedReplyId feedReplyId,
            MemberId fromMemberId,
            Content replyContent,
            NotificationType notificationType,
            LocalDateTime createdAt
    ) {
        this.feedCommentId = feedCommentId;
        this.commentContent = commentContent;
        this.feedReplyId = feedReplyId;
        this.toMemberId = toMemberId;
        this.feedId = feedId;
        this.fromMemberId = fromMemberId;
        this.replyContent = replyContent;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static FeedCommentReplyAddedEvent of(
            FeedCommentId feedCommentId,
            Content commentContent,
            MemberId toMemberId,
            FeedId feedId,
            FeedReplyId feedReplyId,
            MemberId fromMemberId,
            Content replyContent,
            LocalDateTime createdAt
    ) {
        return new FeedCommentReplyAddedEvent(
                feedCommentId,
                commentContent,
                toMemberId,
                feedId,
                feedReplyId,
                fromMemberId,
                replyContent,
                NotificationType.FEED_COMMENT_REPLY_ADDED_EVENT,
                createdAt
        );
    }
}
