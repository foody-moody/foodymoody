package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed_comment.application.FeedCommentReadService;
import com.foodymoody.be.feed_comment.application.ReplyReadService;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import com.foodymoody.be.feed_reply_like.domain.FeedReplyLikeAddedEvent;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.NotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCommentReplyLikeNotificationDetails;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCommentReplyLikeEventHandler {

    private final NotificationWriteService notificationWriteService;
    private final NotificationSettingReadService notificationSettingService;
    private final FeedReadService feedReadService;
    private final FeedCommentReadService feedCommentService;
    private final ReplyReadService replyService;

    @Async
    @EventListener(FeedReplyLikeAddedEvent.class)
    public void saveNotification(FeedReplyLikeAddedEvent event) {
        var feedCommentId = event.getFeedCommentId();
        var feedComment = feedCommentService.findById(feedCommentId);
        var toMemberId = feedComment.getMemberId();
        if (notificationSettingService.isCommentLikedAllowed(toMemberId)) {
            var feedNotificationId = IdFactory.createNotificationId();
            var feedId = feedComment.getFeedId();
            var feed = feedReadService.findFeed(feedId);
            var feedReplyId = event.getFeedReplyId();
            var feedReply = replyService.fetchById(feedReplyId);
            var details = makeDetails(feedId, feed, feedCommentId, feedReplyId, feedReply);
            var feedNotification = toNotification(event, feedNotificationId, details, toMemberId);
            notificationWriteService.save(feedNotification);
        }
    }

    private static NotificationDetails makeDetails(
            FeedId feedId,
            Feed feed,
            FeedCommentId feedCommentId,
            FeedReplyId feedReplyId,
            FeedReply feedReply
    ) {
        return new FeedCommentReplyLikeNotificationDetails(
                feedId,
                feed.getProfileImageUrl(),
                feedCommentId,
                feedReplyId,
                feedReply.getContent()
        );
    }
}
