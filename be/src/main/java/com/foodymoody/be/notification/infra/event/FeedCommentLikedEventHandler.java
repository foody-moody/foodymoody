package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationDetailsFactory.makeDetails;
import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.service.FeedReadService;
import com.foodymoody.be.feed_comment.application.service.FeedCommentReadService;
import com.foodymoody.be.feed_comment_like.domain.FeedCommentLikeAddedEvent;
import com.foodymoody.be.notification.application.service.NotificationWriteService;
import com.foodymoody.be.notification_setting.application.usecase.NotificationSettingReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * The CommentLikedEventHandler class is responsible for handling CommentLikeAddedEvent and saving notifications if
 * comment likes are allowed for the recipient member.
 */
@RequiredArgsConstructor
@Service
public class FeedCommentLikedEventHandler {

    private final NotificationWriteService notificationService;
    private final NotificationSettingReadUseCase settingReadUseCase;
    private final FeedReadService feedService;
    private final FeedCommentReadService feedCommentService;

    @Async
    @EventListener(FeedCommentLikeAddedEvent.class)
    public void saveNotification(FeedCommentLikeAddedEvent event) {
        var feedCommentId = event.getFeedCommentId();
        var feedComment = feedCommentService.findById(feedCommentId);
        var toMemberId = feedComment.getMemberId();
        if (settingReadUseCase.isCommentLikedAllowed(toMemberId)) {
            var feed = feedService.findFeed(feedComment.getFeedId());
            saveNotification(event, feed.getId(), feed.getProfileImageUrl(), feedComment.getContent(), toMemberId);
        }
    }

    private void saveNotification(
            FeedCommentLikeAddedEvent event,
            FeedId feedId,
            String thumbnailUrl,
            Content feedCommentContent,
            MemberId toMemberId
    ) {
        var feedNotificationId = IdFactory.createNotificationId();
        var details = makeDetails(event, feedId, feedCommentContent, thumbnailUrl);
        var feedNotification = toNotification(event, feedNotificationId, details, toMemberId);
        notificationService.save(feedNotification);
    }

}
