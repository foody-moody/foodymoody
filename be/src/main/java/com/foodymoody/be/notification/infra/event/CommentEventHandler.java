package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.LinkMaker.makeFeedLink;
import static com.foodymoody.be.notification.infra.event.MessageMaker.makeCommentAddMessage;
import static com.foodymoody.be.notification.infra.event.NotificationMapper.toNotification;

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
    private final MemberService memberService;
    private final FeedService feedService;

    @EventListener(CommentAddedEvent.class)
    public void saveNotification(CommentAddedEvent event) {
        NotificationId notificationId = NotificationIdFactory.newId();
        Member member = memberService.findById(event.getMemberId());
        Feed feed = feedService.findFeed(event.getFeedId());
        String message = makeCommentAddMessage(member);
        String link = makeFeedLink(feed.getId());
        Notification notification = toNotification(event, notificationId, member, feed, link, message);
        notificationWriteService.save(notification);
    }
}
