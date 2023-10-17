package com.foodymoody.be.docs.feed;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;

class FeedSteps {

    public static ExtractableResponse<Response> 피드를_등록한다(RequestSpecification spec) {
        Map<String, Object> body = Map.of(
                "location", "맛있게 매운 콩볼 범계점",
                "review", "맛있게 먹었습니다.",
                "mood", "기쁨",
                "images", List.of(
                        Map.of(
                                "imageUrl", "https://www.googles.com/",
                                "menu", Map.of(
                                        "name", "마라탕",
                                        "numStar", 4
                                )
                        ),
                        Map.of(
                                "imageUrl", "https://www.google.com/",
                                "menu", Map.of(
                                        "name", "감자탕",
                                        "numStar", 3
                                )
                        )
                )
        );

        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .body(body)
                .when()
                .post("/api/feeds")
                .then()
                .log().all()
                .extract();
    }

    public static void 응답코드가_200이고_id가_1이면_정상적으로_등록된_피드(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getLong("id")).isEqualTo(1)
                //TODO 피드 조회시 등록한 피드가 조회된다.
        );
    }

}
