package com.foodymoody.be.acceptance.notification_setting;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotificationSettingAcceptanceTest extends AcceptanceTest {

    @DisplayName("회원아이디로 알림 설정을 조회한다.")
    @Test
    void findNotificationSettingByMemberId() {
        // docs
        api_문서_타이틀("notificaiton_setting_request_success", spec);

        // when
        var response = RestAssured
                .given().log().all().spec(spec).auth().oauth2(회원아티_액세스토큰)
                .when().get("/api/notification/settings")
                .then().log().all().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
