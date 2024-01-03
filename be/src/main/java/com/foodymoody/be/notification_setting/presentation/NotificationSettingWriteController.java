package com.foodymoody.be.notification_setting.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification_setting.application.NotificationSettingWriteService;
import com.foodymoody.be.notification_setting.application.dto.NotificationSettingUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class NotificationSettingWriteController {

    private final NotificationSettingWriteService service;

    @PutMapping("/api/notification/settings")
    public ResponseEntity<Void> update(
            @CurrentMemberId MemberId memberId,
            @RequestBody NotificationSettingUpdateRequest request
    ) {
        service.update(memberId, request);
        return ResponseEntity.noContent().build();
    }
}
