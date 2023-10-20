package com.foodymoody.be.docs.auth;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class AuthSteps {

    public static void 토큰과_응답코드_200을_응답한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getString("accessToken")).isNotNull(),
                () -> assertThat(response.jsonPath().getString("refreshToken")).isNotNull()
        );
    }

    public static ExtractableResponse<Response> 로그인_한다(String email, RequestSpecification spec) {
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", "password");
        return RestAssured
                .given().spec(spec).log().all()
                .body(body)
                .when().post("/auth/login")
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
}
