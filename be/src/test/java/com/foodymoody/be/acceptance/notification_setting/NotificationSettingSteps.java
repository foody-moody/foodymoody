package com.foodymoody.be.acceptance.notification_setting;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;

public class NotificationSettingSteps {

    public static ExtractableResponse<Response> 알림_설정을_요청한다(String accessToken, RequestSpecification spec) {
        return RestAssured
                .given().log().all().spec(spec).auth().oauth2(accessToken)
                .when().get("/api/notification/settings")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 알림설정을_수정한다(String accessToken, RequestSpecification spec) {

        Map<String, Object> body = new HashMap<>();
        body.put("feedLike", false);
        body.put("collectionLike", false);
        body.put("commentLike", false);
        body.put("follow", false);
        body.put("feedComment", false);
        body.put("collectionComment", false);

        return RestAssured
                .given().log().all().spec(spec).auth().oauth2(accessToken)
                .body(body).contentType("application/json")
                .when().put("/api/notification/settings")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 전체_알림설정을_수정한다(String accessToken, RequestSpecification spec) {
        Map<String, Object> body = new HashMap<>();
        body.put("allow", false);
        return RestAssured
                .given().log().all().spec(spec).auth().oauth2(accessToken)
                .body(body).contentType("application/json")
                .when().put("/api/notification/settings/all")
                .then().log().all().extract();
    }

    public static void 응답코드_204(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(204);
    }

    public static void 알림_설정_조회_검증(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.body().jsonPath().getBoolean("feedComment")).isTrue(),
                () -> assertThat(response.body().jsonPath().getBoolean("collectionComment")).isTrue(),
                () -> assertThat(response.body().jsonPath().getBoolean("collectionLike")).isTrue(),
                () -> assertThat(response.body().jsonPath().getBoolean("commentLike")).isTrue(),
                () -> assertThat(response.body().jsonPath().getBoolean("feedLike")).isTrue(),
                () -> assertThat(response.body().jsonPath().getBoolean("follow")).isTrue()
        );
    }

}
