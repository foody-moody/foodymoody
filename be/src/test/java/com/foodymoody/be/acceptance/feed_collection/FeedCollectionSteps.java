package com.foodymoody.be.acceptance.feed_collection;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedCollectionSteps {

    public static ExtractableResponse<Response> 피드_커렉션_등록한다(
            List<String> feedIds,
            String accessToken
    ) {
        return 피드_커렉션_등록한다(feedIds, accessToken, new RequestSpecBuilder().build());
    }


    public static ExtractableResponse<Response> 피드_커렉션_등록한다(
            List<String> feedIds,
            String accessToken,
            RequestSpecification spec
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("title", "테스트 컬렉션");
        body.put("description", "테스트 컬렉션입니다.");
        body.put("thumbnailUrl", "https://thumbnail.url");
        body.put("private", false);
        body.put("feedIds", feedIds);
        return RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .body(body).contentType("application/json")
                .when()
                .post("/api/collections")
                .then().log().all()
                .extract();
    }
}
