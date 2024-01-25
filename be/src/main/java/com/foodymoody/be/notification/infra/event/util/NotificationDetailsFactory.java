package com.foodymoody.be.notification.infra.event.util;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed.domain.entity.FeedAddedEvent;
import com.foodymoody.be.feed_collection.domain.FeedCollectionAddedEvent;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentAddedEvent;
import com.foodymoody.be.feed_collection_comment_like.domain.FeedCollectionCommentLikeAddedEvent;
import com.foodymoody.be.feed_collection_like.domain.FeedCollectionLikeAddedEvent;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplyAddedEvent;
import com.foodymoody.be.feed_collection_reply_like.domain.FeedCollectionReplyLikeAddedEvent;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentAddedEvent;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentReplyAddedEvent;
import com.foodymoody.be.feed_comment_like.infra.usecase.FeedCommentLikeAddedEvent;
import com.foodymoody.be.feed_like.domain.entity.FeedLikeAddedEvent;
import com.foodymoody.be.feed_reply_like.domain.FeedReplyLikeAddedEvent;
import com.foodymoody.be.notification.domain.NotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionCommentLikeNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionCommentNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionLikeNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionReplyLikeNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionReplyNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCommentLikeNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCommentNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCommentReplyLikeNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCommentReplyNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedLikeNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedNotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.MemberFollowedNotificationDetails;

public class NotificationDetailsFactory {

    private NotificationDetailsFactory() {
        throw new AssertionError();
    }

    public static NotificationDetails makeDetails(
            FeedCollectionCommentAddedEvent event,
            String feedCollectionThumbnailUrl,
            FeedCollectionId feedCollectionId
    ) {
        return new FeedCollectionCommentNotificationDetails(
                feedCollectionId,
                feedCollectionThumbnailUrl,
                event.getFeedCollectionCommentId(),
                event.getFeedCollectionCommentContent()
        );
    }

    public static NotificationDetails makeDetails(
            FeedCollectionCommentLikeAddedEvent event,
            Content feedCollectionCommentContent,
            FeedCollectionId feedCollectionId,
            String feedCollectionThumbnailUrl
    ) {
        return new FeedCollectionCommentLikeNotificationDetails(
                event.getFeedCollectionCommentId(),
                feedCollectionCommentContent,
                feedCollectionId,
                feedCollectionThumbnailUrl
        );
    }

    public static NotificationDetails makeDetails(FeedCollectionAddedEvent event) {
        return new FeedCollectionNotificationDetails(
                event.getFeedCollectionId(),
                event.getFeedCollectionTitle(),
                event.getFeedCollectionThumbnailUrl()
        );
    }

    public static NotificationDetails makeDetails(
            FeedCollectionLikeAddedEvent event,
            String feeCollectionThumbnailUrl
    ) {
        return new FeedCollectionLikeNotificationDetails(
                event.getFeedCollectionId(),
                feeCollectionThumbnailUrl
        );
    }

    public static NotificationDetails makeDetails(
            FeedCollectionReplyAddedEvent event,
            FeedCollectionId feedCollectionId,
            String feedCollectionThumbnailUrl
    ) {
        return new FeedCollectionReplyNotificationDetails(
                feedCollectionId,
                feedCollectionThumbnailUrl,
                event.getToFeedCollectionCommentId(),
                event.getFeedCollectionReplyId(),
                event.getFeedCollectionReplyContent()
        );
    }

    public static NotificationDetails makeDetails(
            FeedCollectionReplyLikeAddedEvent event,
            FeedCollectionId feedCollectionId,
            String feedCollectionThumbnailUrl,
            Content feedCollectionReplyContent
    ) {
        return new FeedCollectionReplyLikeNotificationDetails(
                feedCollectionId,
                feedCollectionThumbnailUrl,
                event.getFeedCollectionCommentId(),
                event.getFeedCollectionReplyId(),
                feedCollectionReplyContent
        );
    }

    public static NotificationDetails mackDetails(
            FeedCommentAddedEvent event,
            FeedId feedId,
            String feedThumbnailUrl
    ) {
        return new FeedCommentNotificationDetails(
                feedId,
                feedThumbnailUrl,
                event.getFeedCommentId(),
                event.getCommentContent()
        );
    }

    public static NotificationDetails makeDetails(
            FeedCommentLikeAddedEvent event,
            FeedId feedId,
            String feedThumbnailUrl
    ) {
        return new FeedCommentLikeNotificationDetails(
                event.getFeedCommentId(),
                event.getCommentContent(),
                feedId,
                feedThumbnailUrl
        );
    }

    public static NotificationDetails makeDetails(FeedCommentReplyAddedEvent event, String feedThumbnailUrl) {
        return new FeedCommentReplyNotificationDetails(
                event.getFeedCommentId(),
                event.getFeedId(),
                feedThumbnailUrl,
                event.getFeedReplyId(),
                event.getReplyContent()
        );
    }

    public static NotificationDetails makeDetails(
            FeedReplyLikeAddedEvent event,
            FeedId feedId,
            String feedThumbnailUrl,
            Content feedReplyContent
    ) {
        return new FeedCommentReplyLikeNotificationDetails(
                feedId,
                feedThumbnailUrl,
                event.getFeedCommentId(),
                event.getFeedReplyId(),
                feedReplyContent
        );
    }

    public static NotificationDetails makeDetails(FeedAddedEvent event) {
        return new FeedNotificationDetails(event.getFeedId(), event.getProfileImageUrl());
    }

    public static NotificationDetails mackDetails(FeedLikeAddedEvent event, String thumbnailUrl) {
        return new FeedLikeNotificationDetails(event.getFeedId(), thumbnailUrl);
    }

    public static NotificationDetails makeDetails(boolean isFollowed) {
        return new MemberFollowedNotificationDetails(isFollowed);
    }
}
