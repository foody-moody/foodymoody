package com.foodymoody.be.acceptance.notification_setting;

import static com.foodymoody.be.acceptance.notification_setting.NotificationSettingSteps.알림_설정_조회_검증;
import static com.foodymoody.be.acceptance.notification_setting.NotificationSettingSteps.알림_설정을_요청한다;
import static com.foodymoody.be.acceptance.notification_setting.NotificationSettingSteps.알림설정을_수정한다;
import static com.foodymoody.be.acceptance.notification_setting.NotificationSettingSteps.응답코드_204;
import static com.foodymoody.be.acceptance.notification_setting.NotificationSettingSteps.전체_알림설정을_수정한다;

import com.foodymoody.be.acceptance.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("알림 설정 관련 기능")
class FeedNotificationSettingAcceptanceTest extends AcceptanceTest {

    @DisplayName("알림 설정을 조회한다.")
    @Test
    void findNotificationSettingByMemberId() {
        // docs
        api_문서_타이틀("notification_setting_request_success", spec);

        // when
        var response = 알림_설정을_요청한다(회원아티_액세스토큰, spec);

        // then
        알림_설정_조회_검증(response);
    }

    @DisplayName("알림 설정을 수정 성공 하면 204를 반환한다.")
    @Test
    void when_request_update_notification_setting_if_success_then_return_code_204() {
        // docs
        api_문서_타이틀("notification_setting_update_success", spec);

        // when
        var response = 알림설정을_수정한다(회원아티_액세스토큰, spec);

        // then
        응답코드_204(response);
    }


    @DisplayName("모든 알림 설정을 off으로 수정 요청 성공 하면 204를 반환한다.")
    @Test
    void when_request_update_all_notification_setting_if_success_then_return_code_204() {
        // docs
        api_문서_타이틀("notification_setting_update_all_success", spec);

        // when
        var response = 전체_알림설정을_수정한다(회원아티_액세스토큰, spec);

        // then
        응답코드_204(response);
    }
}
