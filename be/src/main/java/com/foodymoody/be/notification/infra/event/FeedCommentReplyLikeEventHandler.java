package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationDetailsFactory.makeDetails;
import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.service.FeedReadService;
import com.foodymoody.be.feed_comment.application.service.FeedCommentReadService;
import com.foodymoody.be.feed_comment.application.service.FeedReplyReadService;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_reply_like.domain.FeedReplyLikeAddedEvent;
import com.foodymoody.be.notification.application.service.NotificationWriteService;
import com.foodymoody.be.notification_setting.application.usecase.NotificationSettingReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCommentReplyLikeEventHandler {

    private final NotificationWriteService notificationWriteService;
    private final NotificationSettingReadUseCase settingReadUseCase;
    private final FeedReadService feedReadService;
    private final FeedCommentReadService feedCommentService;
    private final FeedReplyReadService replyService;

    @Async
    @EventListener(FeedReplyLikeAddedEvent.class)
    public void saveNotification(FeedReplyLikeAddedEvent event) {
        var feedCommentId = event.getFeedCommentId();
        var feedComment = feedCommentService.findById(feedCommentId);
        var toMemberId = feedComment.getMemberId();
        if (settingReadUseCase.isCommentLikedAllowed(toMemberId)) {
            saveNotification(event, feedComment, toMemberId);
        }
    }

    private void saveNotification(
            FeedReplyLikeAddedEvent event, FeedComment feedComment, MemberId toMemberId
    ) {
        var feedNotificationId = IdFactory.createNotificationId();
        var feed = feedReadService.findFeed(feedComment.getFeedId());
        var feedReply = replyService.fetchById(event.getFeedReplyId());
        var details = makeDetails(event, feed.getId(), feed.getProfileImageUrl(), feedReply.getContent());
        var feedNotification = toNotification(event, feedNotificationId, details, toMemberId);
        notificationWriteService.save(feedNotification);
    }

}
