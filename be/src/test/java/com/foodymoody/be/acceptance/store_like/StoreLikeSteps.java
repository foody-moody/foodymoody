package com.foodymoody.be.acceptance.store_like;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractStringAssert;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class StoreLikeSteps {

    public static ExtractableResponse<Response> 가게_좋아요를_등록한다(String accessToken, String storeId,
                                                             RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(accessToken)
                .when()
                .post("/api/stores/{storeId}/likes", storeId)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 가게_좋아요를_등록한다(String accessToken, String storeId) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(accessToken)
                .when()
                .post("/api/stores/{storeId}/likes", storeId)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 가게_좋아요를_취소한다(String accessToken, String storeId,
                                                             RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(accessToken)
                .when()
                .delete("/api/stores/{storeId}/likes", storeId)
                .then()
                .log().all()
                .extract();
    }

    public static AbstractIntegerAssert<?> 상태코드를_검증한다(ExtractableResponse<Response> response,
                                                      HttpStatus expectedHttpStatus) {
        return assertThat(response.statusCode()).isEqualTo(expectedHttpStatus.value());
    }

    public static AbstractStringAssert<?> 오류코드를_검증한다(ExtractableResponse<Response> response, String code) {
        return assertThat(response.jsonPath().getString("code")).isEqualTo(code);
    }

}
