package com.foodymoody.be.notification_setting.application.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.foodymoody.be.common.exception.NotificationSettingNotFoundException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.notification_setting.domain.NotificationSettingRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("NotificationSettingReadService 클래스")
@ExtendWith(MockitoExtension.class)
class NotificationSettingWriteServiceTest {

    @InjectMocks
    private NotificationSettingWriteService notificationSettingWriteService;

    @Mock
    private NotificationSettingRepository notificationSettingRepository;

    @DisplayName("맴버 아이디를 받아서 알림 설정을 조회시 알림 설정이 없으면 예외를 반환한다.")
    @Test
    void fetchByMemberId() {
        // given
        var memberId = IdFactory.createMemberId();
        given(notificationSettingRepository.findByMemberId(memberId)).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> notificationSettingWriteService.fetchByMemberId(memberId))
                .isInstanceOf(NotificationSettingNotFoundException.class);
    }
}
