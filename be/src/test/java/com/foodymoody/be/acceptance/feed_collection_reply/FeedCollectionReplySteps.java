package com.foodymoody.be.acceptance.feed_collection_reply;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class FeedCollectionReplySteps {

    public static ExtractableResponse<Response> 피드_컬렉션_댓글에_대댓글을_등록한다(
            String accessToken,
            String commentId,
            RequestSpecification spec
    ) {
        Map<String, String> body = Map.of("content", "대댓글입니다.");
        return RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .body(body).contentType("application/json")
                .when()
                .post("/api/feed_collections/{commentId}/replies", commentId)
                .then().log().all()
                .extract();
    }
}
