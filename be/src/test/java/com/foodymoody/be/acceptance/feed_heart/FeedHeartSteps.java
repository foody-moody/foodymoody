package com.foodymoody.be.acceptance.feed_heart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import org.springframework.http.MediaType;

public class FeedHeartSteps {

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
                .post("/api/feeds/" + feedId + "/likes")
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

    public static ExtractableResponse<Response> 좋아요된_피드에_또_좋아요를_한다(String feedId, String accessToken,
                                                                   RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .auth()
                .oauth2(accessToken)
                .when()
                .post("/api/feeds/" + feedId + "/likes")
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("이미 좋아요 누른 피드입니다."))
                .body("code", equalTo("g001"))
                .extract();
    }


    public static ExtractableResponse<Response> 좋아요_취소를_한다(String feedId, String accessToken,
                                                           RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .auth()
                .oauth2(accessToken)
                .when()
                .delete("/api/feeds/" + feedId + "/likes")
                .then()
                .log().all()
                .extract();
    }

    public static void 응답코드가_204이면_정상적으로_좋아요_취소(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(204);
    }

    public static ExtractableResponse<Response> 좋아요_한_적이_없는데_좋아요_취소를_한다(String feedId, String accessToken,
                                                                        RequestSpecification spec) {
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
                .delete("/api/feeds/" + feedId + "/likes")
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("좋아요 기록이 없어 취소할 수 없습니다."))
                .body("code", equalTo("g001"))
                .extract();
    }

}
