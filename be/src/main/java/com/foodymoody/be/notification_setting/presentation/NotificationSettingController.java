package com.foodymoody.be.notification_setting.presentation;

import com.foodymoody.be.common.annotation.MemberId;
import com.foodymoody.be.notification_setting.application.NotificationSettingReadService;
import com.foodymoody.be.notification_setting.domain.NotificationSettingSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NotificationSettingController {

    private final NotificationSettingReadService notificationSettingReadService;

    @GetMapping("/api/notification/settings")
    public ResponseEntity<NotificationSettingSummary> request(@MemberId String memberId) {
        var notificationSettingSummary = notificationSettingReadService.request(memberId);
        return ResponseEntity.ok(notificationSettingSummary);
    }
}
