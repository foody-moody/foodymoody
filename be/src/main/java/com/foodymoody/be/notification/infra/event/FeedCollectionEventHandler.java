package com.foodymoody.be.notification.infra.event;

import static com.foodymoody.be.notification.infra.event.util.NotificationMapper.toNotification;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.domain.FeedCollectionAddedEvent;
import com.foodymoody.be.member.application.MemberReadService;
import com.foodymoody.be.member.application.dto.FollowMemberSummary;
import com.foodymoody.be.member.domain.FollowRepository;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.NotificationDetails;
import com.foodymoody.be.notification.infra.event.dto.FeedCollectionNotificationDetails;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionEventHandler {

    private final MemberReadService memberService;
    private final FollowRepository followRepository;
    private final NotificationSettingReadService notificationSettingService;
    private final NotificationWriteService notificationService;

    @Async
    @Transactional
    @EventListener(FeedCollectionAddedEvent.class)
    public void handle(FeedCollectionAddedEvent event) {
        var fromMemberId = event.getFromMemberId();
        var fromMember = memberService.findById(fromMemberId);
        Slice<FollowMemberSummary> followMemberSummaries = followRepository.fetchMyFollowerSummariesByMember(
                fromMember, PageRequest.of(0, 100));
        followMemberSummaries.forEach(followMemberSummary -> {
            var toMemberId = followMemberSummary.getId();
            if (notificationSettingService.isFollowedAllowed(toMemberId)) {
                saveEvent(event, toMemberId);
            }
        });
    }

    private void saveEvent(FeedCollectionAddedEvent event, MemberId toMemberId) {
        var feedNotificationId = IdFactory.createNotificationId();
        var details = makeDetails(event);
        var feedNotification = toNotification(event, feedNotificationId, details, toMemberId);
        notificationService.save(feedNotification);
    }

    private static NotificationDetails makeDetails(FeedCollectionAddedEvent event) {
        return new FeedCollectionNotificationDetails(
                event.getFeedCollectionId(),
                event.getFeedCollectionTitle(),
                event.getFeedCollectionThumbnailUrl()
        );
    }

}
