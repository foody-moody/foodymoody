package com.foodymoody.be.acceptance.notification_setting;

import static com.foodymoody.be.acceptance.notification_setting.NotificationSettingSteps.알림_설정_조회_검증;
import static com.foodymoody.be.acceptance.notification_setting.NotificationSettingSteps.알림_설정을_요청한다;
import static com.foodymoody.be.acceptance.notification_setting.NotificationSettingSteps.응답코드_204;

import com.foodymoody.be.acceptance.AcceptanceTest;
import io.restassured.RestAssured;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("알림 설정 관련 기능")
class NotificationSettingAcceptanceTest extends AcceptanceTest {

    @DisplayName("회원아이디로 알림 설정을 조회한다.")
    @Test
    void findNotificationSettingByMemberId() {
        // docs
        api_문서_타이틀("notificaiton_setting_request_success", spec);

        // when
        var response = 알림_설정을_요청한다(회원아티_액세스토큰, spec);

        // then
        알림_설정_조회_검증(response);
    }

    @DisplayName("회원아이디로 알림 설정을 수정한다.")
    @Test
    void updateNotificationSettingByMemberId() {
        // docs
        api_문서_타이틀("notificaiton_setting_update_success", spec);

        // when
        Map<String, Object> body = new HashMap<>();
        body.put("heart", false);
        body.put("comment", false);
        body.put("feed", false);
        var response = RestAssured
                .given().log().all().spec(spec).auth().oauth2(회원아티_액세스토큰)
                .body(body).contentType("application/json")
                .when().put("/api/notification/settings")
                .then().log().all().extract();

        // then
        응답코드_204(response);
    }
}
