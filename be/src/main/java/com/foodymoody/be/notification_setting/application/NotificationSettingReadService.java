package com.foodymoody.be.notification_setting.application;

import com.foodymoody.be.common.exception.NotificationSettingNotFoundException;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification_setting.domain.NotificationSettingRepository;
import com.foodymoody.be.notification_setting.domain.NotificationSettingSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationSettingReadService {

    private final NotificationSettingRepository settingRepository;

    @Transactional(readOnly = true)
    public NotificationSettingSummary request(MemberId memberId) {
        return getSetting(memberId);
    }

    @Transactional(readOnly = true)
    public boolean isCommentAllowed(MemberId memberId) {
        var setting = getSetting(memberId);
        return setting.isFeedComment();
    }

    @Transactional(readOnly = true)
    public boolean isCommentLikedAllowed(MemberId memberId) {
        var setting = getSetting(memberId);
        return setting.isCommentLike();
    }

    @Transactional(readOnly = true)
    public boolean isFeedCollectionCommentRepliedAllowed(MemberId toMemberId) {
        var setting = getSetting(toMemberId);
        return setting.isCollectionComment();
    }

    @Transactional(readOnly = true)
    public boolean isFeedCollectionCommentAddedAllowed(MemberId toMemberId) {
        var setting = getSetting(toMemberId);
        return setting.isCollectionComment();
    }

    @Transactional(readOnly = true)
    public boolean isFollowedAllowed(MemberId toMemberId) {
        var setting = getSetting(toMemberId);
        return setting.isFollow();
    }

    @Transactional(readOnly = true)
    public boolean isFeedCollectionLikeAllowed(MemberId toMemberId) {
        var setting = getSetting(toMemberId);
        return setting.isCollectionLike();
    }

    @Transactional(readOnly = true)
    public boolean isFeedLikedAllowed(MemberId toMemberId) {
        var setting = getSetting(toMemberId);
        return setting.isFeedLike();
    }

    @Transactional(readOnly = true)
    public boolean isFeedCollectionCommentLikeAddedAllowed(MemberId toMemberId) {
        var setting = getSetting(toMemberId);
        return setting.isCommentLike();
    }

    @Transactional(readOnly = true)
    public boolean isFeedCollectionReplyLikeAllowed(MemberId toMemberId) {
        var setting = getSetting(toMemberId);
        return setting.isCommentLike();
    }

    @Transactional(readOnly = true)
    public boolean isMemberFollowedEventEnabled(MemberId toMemberId) {
        var setting = getSetting(toMemberId);
        return setting.isFollow();
    }

    private NotificationSettingSummary getSetting(MemberId toMemberId) {
        return settingRepository.findSummaryByMemberId(toMemberId)
                .orElseThrow(NotificationSettingNotFoundException::new);
    }
}
