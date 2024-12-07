package com.foodymoody.be.feed_collection_comment.domain;

import com.foodymoody.be.common.event.NotificationEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedCollectionCommentAddedEvent implements NotificationEvent {

    private final FeedCollectionId feedCollectionId;
    private final MemberId fromMemberId;
    private final FeedCollectionCommentId feedCollectionCommentId;
    private final Content feedCollectionCommentContent;
    private final NotificationType notificationType;
    private final LocalDateTime createdAt;

    private FeedCollectionCommentAddedEvent(
            FeedCollectionId feedCollectionId,
            MemberId fromMemberId,
            FeedCollectionCommentId feedCollectionCommentId,
            Content feedCollectionCommentContent,
            NotificationType notificationType,
            LocalDateTime createdAt
    ) {
        this.feedCollectionId = feedCollectionId;
        this.fromMemberId = fromMemberId;
        this.feedCollectionCommentId = feedCollectionCommentId;
        this.feedCollectionCommentContent = feedCollectionCommentContent;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static FeedCollectionCommentAddedEvent of(
            FeedCollectionId feedCollectionId,
            MemberId fromMemberId,
            FeedCollectionCommentId feedCollectionCommentId,
            Content feedCollectionCommentContent,
            LocalDateTime createdAt
    ) {
        return new FeedCollectionCommentAddedEvent(
                feedCollectionId,
                fromMemberId,
                feedCollectionCommentId,
                feedCollectionCommentContent,
                NotificationType.FEED_COLLECTION_COMMENT_ADDED_EVENT,
                createdAt
        );
    }

}
