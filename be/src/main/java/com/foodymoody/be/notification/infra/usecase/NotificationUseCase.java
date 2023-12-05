package com.foodymoody.be.notification.infra.usecase;

import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.service.FeedService;
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
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationUseCase {

    private final NotificationWriteService notificationWriteService;
    private final NotificationSettingReadService notificationSettingReadService;
    private final ImageService imageService;
    private final MemberService memberService;
    private final FeedService feedService;
    private final NotificationSummaryReadService notificationSummaryReadService;

    public NotificationResponse read(String memberId, String notificationId) {
        FeedNotification feedNotification = notificationWriteService.read(notificationId);
        Member member = memberService.findById(memberId);
        Image memberProfileImage = imageService.findBy(member.getProfileImageId());
        Feed feed = feedService.findFeed(feedNotification.getFeedId());
        String feedProfileImageId = feed.getImageMenus().get(0).getImageId();
        Image feedProfileImage = imageService.findBy(feedProfileImageId);
        return toResponse(memberId, feedNotification, member, memberProfileImage, feed, feedProfileImage);
    }


    @Transactional(readOnly = true)
    public Slice<NotificationResponse> requestAll(String memberId, Pageable pageable) {
        var notificationSettingSummary = notificationSettingReadService.request(memberId);
        var specification = NotificationSummarySpecs.searchByType(notificationSettingSummary.isComment(),
                notificationSettingSummary.isHeart(), notificationSettingSummary.isFeed());
        Slice<NotificationSummary> notificationSummaries = notificationSummaryReadService.requestAll(memberId,
                specification, pageable);
        List<NotificationResponse> notificationResponses = notificationSummaries.stream()
                .map(notificationSummary -> toResponse(memberId, notificationSummary))
                .collect(Collectors.toList());
        return new SliceImpl<>(notificationResponses, notificationSummaries.getPageable(),
                notificationSummaries.hasNext());
    }

    private static NotificationResponse toResponse(String memberId, NotificationSummary notificationSummary) {
        return new NotificationResponse(
                notificationSummary.getId(),
                new Sender(memberId, notificationSummary.getNickname(), notificationSummary.getMemberImageUrl()),
                new FeedInfoResponse(notificationSummary.getFeedId(), notificationSummary.getFeedImageUrl(),
                        notificationSummary.getCommentId(), notificationSummary.getMessage()),
                notificationSummary.getType(),
                notificationSummary.isRead(),
                notificationSummary.getCreatedAt(),
                notificationSummary.getUpdatedAt()
        );
    }


    private static NotificationResponse toResponse(String memberId, FeedNotification feedNotification, Member member,
            Image memberProfileImage, Feed feed, Image feedProfileImage) {
        return new NotificationResponse(feedNotification.getId().getValue(),
                new Sender(memberId, member.getNickname(), memberProfileImage.getUrl()),
                new FeedInfoResponse(feed.getId(), feedProfileImage.getUrl(),
                        feedNotification.getCommentId().getValue(), feedNotification.getMessage()),
                feedNotification.getType(), feedNotification.isRead(), feedNotification.getCreatedAt(),
                feedNotification.getUpdatedAt());
    }
}
