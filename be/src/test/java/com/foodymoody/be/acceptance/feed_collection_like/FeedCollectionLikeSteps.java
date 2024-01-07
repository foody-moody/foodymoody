package com.foodymoody.be.acceptance.feed_collection_like;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FeedCollectionLikeSteps {

    public static ExtractableResponse<Response> 피드_컬렉션에_좋아요를_등록한다(
            String accessToken,
            String feedCollectionId,
            RequestSpecification spec
    ) {
        return RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .when()
                .post("/api/feed_collections/{feedCollectionId}/likes", feedCollectionId)
                .then().log().all()
                .extract();
    }

    public static String 피드_컬렉션에_좋아요를_등록하고_아이디를_반환한다(
            String accessToken,
            String feedCollectionId
    ) {
        return 피드_컬렉션에_좋아요를_등록한다(accessToken, feedCollectionId, new RequestSpecBuilder().build())
                .jsonPath().getString("id");
    }

    public static ExtractableResponse<Response> 피드_컬렉션에_좋아요를_취소한다(
            String accessToken,
            String feedCollectionId,
            String id,
            RequestSpecification spec
    ) {
        return RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .when()
                .delete("/api/feed_collections/{feedCollectionId}/likes/{id}", feedCollectionId, id)
                .then().log().all()
                .extract();
    }

}
