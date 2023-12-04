package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.LinkMaker.makeFeedLink;
import static com.foodymoody.be.notification.infra.event.util.MessageMaker.makeCommentAddMessage;
import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.comment.domain.entity.CommentAddedEvent;
import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.service.FeedService;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.service.MemberService;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentEventHandler {

    private final NotificationWriteService notificationWriteService;
    private final FeedService feedService;
    private final MemberService memberService;

    @EventListener(CommentAddedEvent.class)
    public void saveNotification(CommentAddedEvent event) {
        NotificationId notificationId = NotificationIdFactory.newId();
        Feed feed = feedService.findFeed(event.getFeedId());
        Member member = memberService.findById(event.getMemberId());
        String message = makeCommentAddMessage(member.getNickname());
        String link = makeFeedLink(feed.getId());
        Notification notification = toNotification(notificationId, link, message, event.getMemberId(),
                feed.getMemberId(), event.getNotificationType(), event.getCreatedAt());
        notificationWriteService.save(notification);
    }
}
