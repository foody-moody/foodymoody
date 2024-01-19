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
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import com.foodymoody.be.feed_reply_like.domain.FeedReplyLikedAddedEvent;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCommentReplyLikedEventHandler {

    private final NotificationWriteService notificationWriteService;
    private final NotificationSettingReadService notificationSettingService;
    private final FeedReadService feedReadService;
    private final FeedCommentReadService feedCommentService;
    private final ReplyReadService replyService;

    @Async
    @EventListener(FeedReplyLikedAddedEvent.class)
    public void saveNotification(FeedReplyLikedAddedEvent event) {
        var feedCommentId = event.getFeedCommentId();
        var feedComment = feedCommentService.findById(feedCommentId);
        var toMemberId = feedComment.getMemberId();
        if (notificationSettingService.isCommentLikedAllowed(toMemberId)) {
            var feedNotificationId = IdFactory.createNotificationId();
            var feedId = feedComment.getFeedId();
            var feed = feedReadService.findFeed(feedId);
            var feedReplyId = event.getFeedReplyId();
            var feedReply = replyService.fetchById(feedReplyId);
            var details = makeDetails(feedId, feed, feedCommentId, feedComment, feedReplyId, feedReply);
            var feedNotification = toNotification(event, feedNotificationId, details, toMemberId);
            notificationWriteService.save(feedNotification);
        }
    }

    private static HashMap<String, Object> makeDetails(
            FeedId feedId,
            Feed feed,
            FeedCommentId feedCommentId,
            FeedComment feedComment,
            FeedReplyId feedReplyId,
            FeedReply feedReply
    ) {
        var details = new HashMap<String, Object>();
        details.put("feedId", feedId);
        details.put("feedThumbnail", feed.getProfileImageUrl());
        details.put("commentId", feedCommentId);
        details.put("commentContent", feedComment.getContent());
        details.put("replyId", feedReplyId);
        details.put("replyContent", feedReply.getContent());
        return details;
    }
}
