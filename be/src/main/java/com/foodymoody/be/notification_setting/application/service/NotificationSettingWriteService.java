package com.foodymoody.be.notification_setting.application.service;

import static com.foodymoody.be.notification_setting.application.service.NotificationMapper.toNotificationSetting;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.NotificationSettingId;
import com.foodymoody.be.notification_setting.application.service.dto.NotificationSettingUpdateRequest;
import com.foodymoody.be.notification_setting.domain.NotificationSetting;
import com.foodymoody.be.notification_setting.domain.NotificationSettingIdFactory;
import com.foodymoody.be.notification_setting.domain.NotificationSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationSettingWriteService {

    private final NotificationSettingRepository notificationSettingRepository;

    @Transactional
    public void save(MemberId memberid) {
        NotificationSettingId notificationSettingId = NotificationSettingIdFactory.newId();
        NotificationSetting notificationSetting = toNotificationSetting(memberid, notificationSettingId);
        notificationSettingRepository.save(notificationSetting);
    }

    @Transactional
    public void update(MemberId memberId, NotificationSettingUpdateRequest request) {
        NotificationSetting notificationSetting = getNotificationSettingByMemberId(memberId);
        notificationSetting.update(request.isFeedLike(), request.isCollectionLike(), request.isCommentLike(),
                                   request.isFollow(), request.isFeedComment(), request.isCollectionComment()
        );
    }

    @Transactional
    public void updateAll(MemberId memberId, boolean allow) {
        NotificationSetting notificationSetting = getNotificationSettingByMemberId(memberId);
        notificationSetting.updateAll(allow);
    }

    private NotificationSetting getNotificationSettingByMemberId(MemberId memberId) {
        return notificationSettingRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("알림 설정이 존재하지 않습니다."));
    }
}