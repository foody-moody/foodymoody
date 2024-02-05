package com.foodymoody.be.notification_setting.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification_setting.infra.usecase.NotificationSettingReadUseCase;
import com.foodymoody.be.notification_setting.infra.usecase.NotificationSettingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NotificationSettingReadController {

    private final NotificationSettingReadUseCase useCase;

    @GetMapping("/api/notification/settings")
    public ResponseEntity<NotificationSettingResponse> request(@CurrentMemberId MemberId memberId) {
        var notificationSettingSummary = useCase.request(memberId);
        return ResponseEntity.ok(notificationSettingSummary);
    }
}
