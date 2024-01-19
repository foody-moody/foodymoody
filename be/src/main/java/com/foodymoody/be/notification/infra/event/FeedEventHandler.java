package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.domain.entity.FeedAddedEvent;
import com.foodymoody.be.member.application.MemberQueryService;
import com.foodymoody.be.member.application.dto.FollowMemberSummary;
import com.foodymoody.be.member.domain.FollowRepository;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedEventHandler {

    private final MemberQueryService memberService;
    private final FollowRepository followRepository;
    private final NotificationSettingReadService notificationSettingService;
    private final NotificationWriteService notificationService;

    @Async
    @EventListener(FeedAddedEvent.class)
    public void handle(FeedAddedEvent event) {
        MemberId fromMemberId = event.getFromMemberId();
        var fromMember = memberService.findById(fromMemberId);
        Slice<FollowMemberSummary> followMemberSummaries = followRepository.fetchMyFollowerSummariesByMember(
                fromMember, PageRequest.of(0, 100));
        followMemberSummaries.forEach(followMemberSummary -> {
            var toMemberId = followMemberSummary.getId();
            if (notificationSettingService.isFollowedAllowed(toMemberId)) {
                saveNotification(event, toMemberId);
            }
        });
    }

    private void saveNotification(FeedAddedEvent event, MemberId toMemberId) {
        var NotificationId = IdFactory.createNotificationId();
        var details = makeDetails(event);
        var feedNotification = toNotification(event, NotificationId, details, toMemberId);
        notificationService.save(feedNotification);
    }

    private static HashMap<String, Object> makeDetails(FeedAddedEvent event) {
        var details = new HashMap<String, Object>();
        details.put("feedId", event.getFeedId());
        details.put("feedThumbnail", event.getProfileImageUrl());
        return details;
    }
}
