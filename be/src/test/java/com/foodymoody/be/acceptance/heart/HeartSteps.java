package com.foodymoody.be.acceptance.heart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import org.springframework.http.MediaType;

public class HeartSteps {

    public static ExtractableResponse<Response> 좋아요를_한다(String feedId, String accessToken, RequestSpecification spec) {
        Map<String, Object> body = Map.of(
                "feedId", feedId
        );

        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .auth()
                .oauth2(accessToken)
                .body(body)
                .when()
                .post("/api/likes")
                .then()
                .log().all()
                .extract();
    }

    public static void 응답코드가_200이고_id가_존재하면_정상적으로_좋아요_가능(ExtractableResponse<Response> response) {
        String id = response.jsonPath().getString("id");

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                assertThat(id)::isNotNull
        );
    }

    public static ExtractableResponse<Response> 좋아요된_피드에_또_좋아요를_한다(String feedId, String accessToken, RequestSpecification spec) {
        Map<String, Object> body = Map.of(
                "feedId", feedId
        );

        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .auth()
                .oauth2(accessToken)
                .body(body)
                .when()
                .post("/api/likes")
                .then()
                .log().all()
                .statusCode(400)
                .extract();
    }



    public static ExtractableResponse<Response> 좋아요_취소를_한다(String feedId, String accessToken, RequestSpecification spec) {
        Map<String, Object> body = Map.of(
                "feedId", feedId
        );

        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .auth()
                .oauth2(accessToken)
                .body(body)
                .when()
                .delete("/api/likes")
                .then()
                .log().all()
                .extract();
    }

    public static void 응답코드가_204이고_id가_존재하면_정상적으로_좋아요_취소(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(204);
    }

}
