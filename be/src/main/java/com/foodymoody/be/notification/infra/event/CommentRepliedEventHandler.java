package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.comment.domain.entity.CommentRepliedAddedEvent;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.service.MemberService;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.domain.NotificationId;
import com.foodymoody.be.notification.domain.NotificationIdFactory;
import com.foodymoody.be.notification.infra.event.util.LinkMaker;
import com.foodymoody.be.notification.infra.event.util.MessageMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentRepliedEventHandler {

    private final NotificationWriteService notificationWriteService;
    private final MemberService memberService;

    @EventListener(CommentRepliedAddedEvent.class)
    public void saveNotification(CommentRepliedAddedEvent event) {
        NotificationId notificationId = NotificationIdFactory.newId();
        String link = LinkMaker.makeCommentLink(event.getId());
        Member member = memberService.findById(event.getFromMemberId());
        String message = MessageMaker.makeRepliedAddedMessage(member.getNickname());
        Notification notification = toNotification(notificationId, link, message, event.getFromMemberId(),
                event.getToMemberId(), NotificationType.REPLY_ADDED, event.getCreatedAt());
        notificationWriteService.save(notification);
    }
}
