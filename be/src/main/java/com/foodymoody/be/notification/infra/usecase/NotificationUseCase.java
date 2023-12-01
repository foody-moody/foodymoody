package com.foodymoody.be.notification.infra.usecase;

import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.service.ImageService;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.service.MemberService;
import com.foodymoody.be.notification.application.NotificationReadService;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.Notification;
import com.foodymoody.be.notification.presentation.dto.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationUseCase {

    private final NotificationWriteService notificationWriteService;
    private final NotificationReadService notificationReadService;
    private final ImageService imageService;
    private final MemberService memberService;

    public NotificationResponse requestOne(String memberId, String notificationId) {
        Notification notification = notificationWriteService.read(notificationId);
        Member member = memberService.findById(memberId);
        Image image = imageService.findBy(member.getProfileImageId());
        return new NotificationResponse(notification.getId(), member.getMemberId(), member.getNickname(),
                image.getUrl(), notification.getLink(), notification.getMessage(),
                notification.getCreatedAt(), notification.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public Slice<NotificationResponse> requestAll(String memberId, Pageable pageable) {
        Slice<Notification> notifications = notificationReadService.requestAll(memberId, pageable);
        return notifications.map(notification -> {
            Member member = memberService.findById(notification.getFromMemberId());
            Image image = imageService.findBy(member.getProfileImageId());
            return new NotificationResponse(notification.getId(), member.getMemberId(), member.getNickname(),
                    image.getUrl(), notification.getLink(), notification.getMessage(),
                    notification.getCreatedAt(), notification.getUpdatedAt());
        });
    }
}
