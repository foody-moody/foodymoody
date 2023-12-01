package com.foodymoody.be.notification.infra.event;

import com.foodymoody.be.comment.domain.entity.CommentAddNotificationEvent;
import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.service.FeedService;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.service.MemberService;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentNotificationHandler {

    private final NotificationWriteService notificationWriteService;
    private final MemberService memberService;
    private final FeedService feedService;

    @EventListener(CommentAddNotificationEvent.class)
    public void saveNotification(CommentAddNotificationEvent event) {
        NotificationId notificationId = NotificationIdFactory.newId();
        Member member = memberService.findById(event.getMemberId());
        Feed feed = feedService.findFeed(event.getFeedId());
        String message = String.format("%s님이 %s %s에 댓글을 남겼습니다.", member.getNickname(), event.getCreatedAt().format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)), feed.getId());
        Notification notification = new Notification(notificationId, feed.getMemberId(), message,
                event.getNotificationType(), false, false, event.getCreatedAt(), event.getCreatedAt());
        notificationWriteService.save(notification);
    }
}
