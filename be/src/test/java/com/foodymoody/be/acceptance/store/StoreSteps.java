package com.foodymoody.be.acceptance.store;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractStringAssert;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class StoreSteps {

    public static ExtractableResponse<Response> 식당_상세정보를_조회한다(RequestSpecification spec, String id) {
        return RestAssured.given().log().all().spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/stores/{id}", id)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 식당을_검색한다(RequestSpecification spec, String query) {
        return RestAssured.given().log().all().spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .params("query", query)
                .get("/api/stores/search")
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
