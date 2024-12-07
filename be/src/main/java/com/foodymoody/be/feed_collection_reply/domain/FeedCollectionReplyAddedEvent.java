package com.foodymoody.be.feed_collection_reply.domain;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedCollectionReplyAddedEvent implements NotificationEvent {

    private final FeedCollectionCommentId toFeedCollectionCommentId;
    private final MemberId fromMemberId;
    private final FeedCollectionReplyId feedCollectionReplyId;
    private final Content feedCollectionReplyContent;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private FeedCollectionReplyAddedEvent(
            FeedCollectionCommentId toFeedCollectionCommentId,
            MemberId fromMemberId,
            FeedCollectionReplyId feedCollectionReplyId,
            Content feedCollectionReplyContent,
            NotificationType notificationType,
            LocalDateTime createdAt
    ) {
        this.toFeedCollectionCommentId = toFeedCollectionCommentId;
        this.fromMemberId = fromMemberId;
        this.feedCollectionReplyId = feedCollectionReplyId;
        this.feedCollectionReplyContent = feedCollectionReplyContent;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static FeedCollectionReplyAddedEvent of(
            FeedCollectionCommentId toFeedCollectionCommentId,
            MemberId fromMemberId,
            FeedCollectionReplyId feedCollectionReplyId,
            Content feedCollectionReplyContent,
            LocalDateTime createdAt
    ) {
        return new FeedCollectionReplyAddedEvent(
                toFeedCollectionCommentId,
                fromMemberId,
                feedCollectionReplyId,
                feedCollectionReplyContent,
                NotificationType.FEED_COLLECTION_COMMENT_REPLY_ADDED_EVENT,
                createdAt
        );
    }

}
