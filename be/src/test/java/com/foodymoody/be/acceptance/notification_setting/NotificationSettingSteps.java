package com.foodymoody.be.acceptance.notification_setting;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

public class NotificationSettingSteps {

    public static ExtractableResponse<Response> 알림_설정을_요청한다(String accessToken, RequestSpecification spec) {
        return RestAssured
                .given().log().all().spec(spec).auth().oauth2(accessToken)
                .when().get("/api/notification/settings")
                .then().log().all().extract();
    }

    public static void 응답코드_204(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(204);
    }

    public static void 알림_설정_조회_검증(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.body().jsonPath().getString("id")).isNotNull(),
                () -> assertThat(response.body().jsonPath().getBoolean("heart")).isTrue(),
                () -> assertThat(response.body().jsonPath().getBoolean("comment")).isTrue(),
                () -> assertThat(response.body().jsonPath().getBoolean("feed")).isTrue()
        );
    }
}
