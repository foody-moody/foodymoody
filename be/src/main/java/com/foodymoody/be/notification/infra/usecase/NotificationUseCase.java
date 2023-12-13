package com.foodymoody.be.notification.infra.usecase;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.service.ImageService;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.service.MemberService;
import com.foodymoody.be.notification.application.NotificationSummaryReadService;
import com.foodymoody.be.notification.application.NotificationSummarySpecs;
import com.foodymoody.be.notification.application.NotificationWriteService;
import com.foodymoody.be.notification.domain.FeedNotification;
import com.foodymoody.be.notification.domain.NotificationSummary;
import com.foodymoody.be.notification.presentation.dto.FeedInfoResponse;
import com.foodymoody.be.notification.presentation.dto.NotificationResponse;
import com.foodymoody.be.notification.presentation.dto.Sender;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import com.foodymoody.be.notification_setting.domain.NotificationSettingSummary;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationUseCase {

    private final NotificationWriteService notificationWriteService;
    private final NotificationSettingReadService notificationSettingReadService;
    private final ImageService imageService;
    private final MemberService memberService;
    private final FeedReadService feedReadService;
    private final NotificationSummaryReadService notificationSummaryReadService;


    public NotificationResponse request(String memberIdValue, String notificationId) {
        FeedNotification feedNotification = notificationWriteService.read(notificationId);
        MemberId memberId = IdFactory.createMemberId(memberIdValue);
        Member member = memberService.findById(memberId);
        Image memberProfileImage = imageService.findById(member.getMemberProfileImageId());
        Feed feed = feedReadService.findFeed(feedNotification.getFeedId());
        return toResponse(memberId, feedNotification, member, memberProfileImage, feed, feed.getProfileImageUrl());
    }

    @Transactional(readOnly = true)
    public Slice<NotificationResponse> requestAll(String memberIdValue, Pageable pageable) {
        MemberId memberId = IdFactory.createMemberId(memberIdValue);
        var notificationSettingSummary = notificationSettingReadService.request(memberId);
        var specification = getNotificationSummarySpecification(notificationSettingSummary);
        var notificationSummaries = notificationSummaryReadService.requestAll(
                memberId,
                specification,
                pageable
        );
        var notificationResponses = notificationSummaries.stream()
                .map(notificationSummary -> toResponse(memberId, notificationSummary))
                .collect(Collectors.toList());
        return new SliceImpl<>(notificationResponses, notificationSummaries.getPageable(),
                               notificationSummaries.hasNext()
        );
    }

    private static Specification<NotificationSummary> getNotificationSummarySpecification(
            NotificationSettingSummary notificationSettingSummary
    ) {
        return NotificationSummarySpecs.searchByType(
                notificationSettingSummary.isFeedComment(),
                notificationSettingSummary.isCollectionComment(),
                notificationSettingSummary.isFeedLike(),
                notificationSettingSummary.isCollectionLike(),
                notificationSettingSummary.isCommentLike(),
                notificationSettingSummary.isFollow()
        );
    }

    private static NotificationResponse toResponse(MemberId memberId, NotificationSummary notificationSummary) {
        Sender sender = new Sender(memberId.getValue(), notificationSummary.getNickname(),
                                   notificationSummary.getMemberImageUrl()
        );
        FeedInfoResponse target = new FeedInfoResponse(
                notificationSummary.getFeedId(),
                notificationSummary.getFeedImageUrl(),
                notificationSummary.getCommentId(),
                notificationSummary.getMessage()
        );
        return new NotificationResponse(notificationSummary.getId(), sender, target, notificationSummary.getType(),
                                        notificationSummary.isRead(), notificationSummary.getCreatedAt(),
                                        notificationSummary.getUpdatedAt()
        );
    }


    private static NotificationResponse toResponse(
            MemberId memberId, FeedNotification feedNotification, Member member, Image memberProfileImage, Feed feed,
            String feedProfileImage
    ) {
        Sender sender = new Sender(memberId.getValue(), member.getNickname(), memberProfileImage.getUrl());
        FeedInfoResponse target = new FeedInfoResponse(feed.getId().getValue(), feedProfileImage,
                                                       feedNotification.getCommentId().getValue(),
                                                       feedNotification.getMessage()
        );
        return new NotificationResponse(feedNotification.getId().getValue(), sender, target, feedNotification.getType(),
                                        feedNotification.isRead(), feedNotification.getCreatedAt(),
                                        feedNotification.getUpdatedAt()
        );
    }
}
