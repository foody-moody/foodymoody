package com.foodymoody.be.acceptance.sse;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

public class SseSteps {

    public static ExtractableResponse<Response> sse_연결_요청(RequestSpecification spec, String 회원아티_액세스토큰) {
        return RestAssured.given().log().all().spec(spec).auth().oauth2(회원아티_액세스토큰)
                .when().get("/api/sse")
                .then().log().all()
                .extract();
    }

    public static void 응답코드가_200(ExtractableResponse<Response> response) {
        Assertions.assertAll(() -> assertThat(response.statusCode()).isEqualTo(200));
    }
}
