package com.foodymoody.be.notification_setting.application.usecase;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification_setting.application.service.NotificationSettingReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationSettingReadUseCase {

    private final NotificationSettingReadService settingService;

    public NotificationSettingResponse request(MemberId memberId) {
        var summary = settingService.fetchByMemberId(memberId);
        return NotificationSettingReadUseCaseMapper.toResponse(summary);
    }

    public boolean isCommentAllowed(MemberId memberId) {
        var setting = settingService.fetchByMemberId(memberId);
        return setting.isFeedComment();
    }


    public boolean isCommentLikedAllowed(MemberId memberId) {
        var setting = settingService.fetchByMemberId(memberId);
        return setting.isCommentLike();
    }

    public boolean isFeedCollectionCommentRepliedAllowed(MemberId toMemberId) {
        var setting = settingService.fetchByMemberId(toMemberId);
        return setting.isCollectionComment();
    }

    public boolean isFeedCollectionCommentAddedAllowed(MemberId toMemberId) {
        var setting = settingService.fetchByMemberId(toMemberId);
        return setting.isCollectionComment();
    }

    public boolean isFollowedAllowed(MemberId toMemberId) {
        var setting = settingService.fetchByMemberId(toMemberId);
        return setting.isFollow();
    }

    public boolean isFeedCollectionLikeAllowed(MemberId toMemberId) {
        var setting = settingService.fetchByMemberId(toMemberId);
        return setting.isCollectionLike();
    }

    public boolean isFeedLikedAllowed(MemberId toMemberId) {
        var setting = settingService.fetchByMemberId(toMemberId);
        return setting.isFeedLike();
    }

    public boolean isFeedCollectionCommentLikeAddedAllowed(MemberId toMemberId) {
        var setting = settingService.fetchByMemberId(toMemberId);
        return setting.isCommentLike();
    }


    public boolean isFeedCollectionReplyLikeAllowed(MemberId toMemberId) {
        var setting = settingService.fetchByMemberId(toMemberId);
        return setting.isCommentLike();
    }

    public boolean isMemberFollowedEventEnabled(MemberId toMemberId) {
        var setting = settingService.fetchByMemberId(toMemberId);
        return setting.isFollow();
    }
}
