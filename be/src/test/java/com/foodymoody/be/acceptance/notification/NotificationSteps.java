package com.foodymoody.be.acceptance.notification;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;

public class NotificationSteps {

    public static ExtractableResponse<Response> 회원의_모든_알림을_조회한다(String accessToken) {
        return 회원의_모든_알림을_조회한다(accessToken, new RequestSpecBuilder().build());
    }

    public static ExtractableResponse<Response> 회원의_모든_알림을_조회한다(String accessToken, RequestSpecification spec) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", 0);
        params.put("size", 100);
        return RestAssured
                .given().log().all().spec(spec).auth().oauth2(accessToken).params(params)
                .when().get("/api/notifications")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 알림을_읽음으로_변경(
            String 알림_아이디, String accessToken,
            RequestSpecification spec
    ) {
        return RestAssured
                .given()
                .log().all()
                .spec(spec)
                .auth().oauth2(accessToken)
                .contentType("application/json;charset=UTF-8")
                .accept("application/json;charset=UTF-8")
                .when().put("/api/notifications/{notificationId}/read-status", 알림_아이디)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 모든_알림을_읽음으로_변경한다(
            String accessToken,
            RequestSpecification spec
    ) {
        return RestAssured
                .given()
                .log().all()
                .spec(spec)
                .auth().oauth2(accessToken)
                .contentType("application/json;charset=UTF-8")
                .accept("application/json;charset=UTF-8")
                .when()
                .put("/api/notifications/read-status")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 유저의_모든_읽음_알림을_삭제한다(String accessToken, RequestSpecification spec) {
        return RestAssured
                .given()
                .log().all()
                .spec(spec).auth().oauth2(accessToken)
                .when()
                .delete("/api/notifications/read-status")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 알림을_삭제한다(String 알림_아이디, String accessToken, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec).auth().oauth2(accessToken).when()
                .delete("/api/notifications/{eventId}", 알림_아이디).then().log().all().extract();
    }

    public static ExtractableResponse<Response> 알림을_일괄적으로_삭졔한다(
            List<String> 알림들, String accessToken,
            RequestSpecification spec
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("notificationIds", 알림들);
        return RestAssured.given().log().all().spec(spec).auth().oauth2(accessToken)
                .body(body).contentType("application/json;charset=UTF-8")
                .when().delete("/api/notifications")
                .then().log().all().extract();
    }

    public static String 회원의_모든_알림을_조회하고_첫번째_알림을_가져온다(String accessToken) {
        return 회원의_모든_알림을_조회한다(accessToken).jsonPath().getList("content.notificationId", String.class).get(0);
    }

    public static void 응답코드가_204(ExtractableResponse<Response> response) {
        Assertions.assertAll(() -> assertThat(response.statusCode()).isEqualTo(204));
    }

    public static void 응답코드가_200(ExtractableResponse<Response> response) {
        Assertions.assertAll(() -> assertThat(response.statusCode()).isEqualTo(200));
    }

    public static ExtractableResponse 알림_아이디로_알림을_조회한다(String 알림_아이디, String accessToken, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec).auth().oauth2(accessToken)
                .when().get("/api/notifications/{notificationId}", 알림_아이디)
                .then().log().all()
                .extract();
    }

}
