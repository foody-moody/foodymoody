package com.foodymoody.be.acceptance.auth;

import static com.foodymoody.be.acceptance.notification.NotificationSteps.회원의_모든_알람을_조회한다;
import static com.foodymoody.be.member.util.MemberFixture.회원_푸반;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.auth.controller.dto.LoginRequest;
import com.foodymoody.be.auth.util.AuthFixture;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractStringAssert;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class AuthSteps {

    public static ExtractableResponse<Response> 비회원보노가_로그인한다(RequestSpecification spec) {
        return 로그인한다(AuthFixture.보노_로그인_요청(), spec);
    }

    public static ExtractableResponse<Response> 푸반이_로그인한다(RequestSpecification spec) {
        return 로그인한다(AuthFixture.푸반_로그인_요청(), spec);
    }

    public static ExtractableResponse<Response> 회원푸반이_틀린_비밀번호로_로그인한다(RequestSpecification spec) {
        return 로그인_한다(회원_푸반.getEmail(), "wrongPassword", spec);
    }

    public static ExtractableResponse<Response> 로그인한다(LoginRequest request, RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .body(request)
                .when().post("/api/auth/login")
                .then().log().all()
                .extract();
    }

    /**
     * @deprecated 로그인한다(LoginRequest request, RequestSpecification spec)을 사용해주세요
     * */
    @Deprecated(forRemoval = true)
    public static ExtractableResponse<Response> 로그인_한다(String email, String password, RequestSpecification spec) {
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .body(body)
                .when().post("/api/auth/login")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 토큰을_재발급한다(String refreshToken, RequestSpecification spec) {
        return RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .body(Map.of("refreshToken", refreshToken))
                .when()
                .post("/api/auth/token")
                .then()
                .log().all()
                .extract();
    }

    public static void 상태코드_404와_오류코드_m001을_반환하는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.NOT_FOUND),
                () -> 오류코드를_검증한다(response, "m001")
        );
    }

    public static void 상태코드_401과_오류코드_a005를_반환하는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.UNAUTHORIZED),
                () -> 오류코드를_검증한다(response, "a005")
        );
    }

    public static void 토큰과_상태코드_200을_응답하는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getString("accessToken")).isNotNull(),
                () -> assertThat(response.jsonPath().getString("refreshToken")).isNotNull()
        );
    }

    public static void 상태코드가_200이고_새로운_토큰이_발급됐음을_검증한다(ExtractableResponse<Response> reIssueResponse, ExtractableResponse<Response> loginResponse) {

        String loginAccessToken = loginResponse.jsonPath().getString("accessToken");
        String loginRefreshToken = loginResponse.jsonPath().getString("refreshToken");

        String reIssuedAccessToken = reIssueResponse.jsonPath().getString("accessToken");
        String reIssuedRefreshToken = reIssueResponse.jsonPath().getString("refreshToken");

        Assertions.assertAll(
                () -> 상태코드를_검증한다(reIssueResponse, HttpStatus.OK),
                () -> assertThat(reIssuedAccessToken).isNotEqualTo(loginAccessToken),
                () -> assertThat(reIssuedRefreshToken).isNotEqualTo(loginRefreshToken)
        );
    }

    public static void 상태코드가_400이고_오류코드가_a002임을_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.BAD_REQUEST),
                () -> 오류코드를_검증한다(response, "a002")
        );
    }

    public static void 상태코드가_204임을_검증한다(ExtractableResponse<Response> response) {
        상태코드를_검증한다(response, HttpStatus.NO_CONTENT);
    }

    public static void 로그아웃한_푸반의_액세스토큰으로_알람이_조회가_안됨을_검증한다(String accessToken) {
        ExtractableResponse<Response> 푸반_알람조회_응답 = 회원의_모든_알람을_조회한다(accessToken, new RequestSpecBuilder().build());
        Assertions.assertAll(
                () -> assertThat(푸반_알람조회_응답.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value())
        );
    }

    public static ExtractableResponse<Response> 로그아웃_한다(String accessToken, RequestSpecification spec) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .auth().oauth2(accessToken)
                .when().post("/api/auth/logout")
                .then().log().all()
                .extract();
    }

    private static AbstractStringAssert<?> 오류코드를_검증한다(ExtractableResponse<Response> response, String code) {
        return assertThat(response.jsonPath().getString("code")).isEqualTo(code);
    }

    private static AbstractIntegerAssert<?> 상태코드를_검증한다(ExtractableResponse<Response> response,
            HttpStatus expectedHttpStatus) {
        return assertThat(response.statusCode()).isEqualTo(expectedHttpStatus.value());
    }
}
