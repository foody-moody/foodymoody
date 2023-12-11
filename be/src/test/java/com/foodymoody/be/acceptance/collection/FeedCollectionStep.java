package com.foodymoody.be.acceptance.collection;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import java.util.Map;

public class FeedCollectionStep {

    static ExtractableResponse<Response> 컬렉션_등록한다(RequestSpecification spec, String accessToken,
            List<String> feedIds) {
        Map<String, Object> body = Map.of(
                "title", "컬렉션 제목",
                "description", "컬렉션 설명",
                "thumbnailUrl", "썸네일 URL",
                "feedIds", feedIds,
                "isPrivate", false
        );
        return RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .body(body).contentType("application/json")
                .when()
                .post("/api/collections")
                .then()
                .log().all()
                .extract();
    }
}
