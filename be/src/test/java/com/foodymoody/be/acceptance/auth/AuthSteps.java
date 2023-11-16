package com.foodymoody.be.acceptance.auth;

import static com.foodymoody.be.member.util.MemberFixture.비회원_보노;
import static com.foodymoody.be.member.util.MemberFixture.회원_푸반;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
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
        return 로그인_한다(비회원_보노.getEmail(), 비회원_보노.getPassword(), spec);
    }

    public static ExtractableResponse<Response> 회원푸반이_로그인한다(RequestSpecification spec) {
        return 로그인_한다(회원_푸반.getEmail(), 회원_푸반.getPassword(), spec);
    }

    public static ExtractableResponse<Response> 회원푸반이_틀린_비밀번호로_로그인한다(RequestSpecification spec) {
        return 로그인_한다(회원_푸반.getEmail(), "wrongPassword", spec);
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

    public static void 응답코드_204를_응답한다(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(204);
    }

    public static ExtractableResponse<Response> 로그아웃_한다(String accessToken, RequestSpecification spec1) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .spec(spec1)
                .when().post("/auth/logout")
                .then().log().all()
                .extract();
    }

    private static AbstractStringAssert<?> 오류코드를_검증한다(ExtractableResponse<Response> response, String code) {
        return assertThat(response.jsonPath().getString("code")).isEqualTo(code);
    }

    private static AbstractIntegerAssert<?> 상태코드를_검증한다(ExtractableResponse<Response> response, HttpStatus expectedHttpStatus) {
        return assertThat(response.statusCode()).isEqualTo(expectedHttpStatus.value());
    }
}
