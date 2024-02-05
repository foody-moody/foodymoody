package com.foodymoody.be.acceptance.feed_collection_comment;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;

public class FeedCollectionCommentSteps {

    public static ExtractableResponse<Response> 피드_컬렉션에_댓글을_등록한다(
            String accessToken,
            String feedCollectionId,
            RequestSpecification spec
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("content", "댓글 내용");
        return RestAssured
                .given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .body(body).contentType("application/json")
                .when()
                .post("/api/feed_collections/{id}/comments", feedCollectionId)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 로그인_상태에서_피드_컬렉션의_댓글을_조회한다(
            String accessToken,
            String feedCollectionId,
            RequestSpecification spec
    ) {
        return RestAssured
                .given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .when()
                .get("/api/feed_collections/{id}/comments?size={size}&page={page}", feedCollectionId, 20, 0)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 로그인_하지_않은_상태에서_피드_컬렉션의_댓글을_조회한다(
            String feedCollectionId,
            RequestSpecification spec
    ) {
        return RestAssured
                .given()
                .spec(spec)
                .log().all()
                .when()
                .get("/api/feed_collections/{id}/comments?size={size}&page={page}", feedCollectionId, 20, 0)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드_컬렉션에_댓글을_삭제한다(
            String accessToken,
            String feedCollectionId,
            String id, RequestSpecification spec
    ) {
        return RestAssured
                .given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .when()
                .delete("/api/feed_collections/{feedCollectionId}/comments/{id}", feedCollectionId, id)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드_컬렉션에_댓글을_등록한다(
            String accessToken,
            String feedCollectionId
    ) {
        return 피드_컬렉션에_댓글을_등록한다(accessToken, feedCollectionId, new RequestSpecBuilder().build());
    }

    public static String 피드_컬렉션에_댓글을_등록하고_아이디를_받는다(
            String accessToken,
            String feedCollectionId
    ) {
        return 피드_컬렉션에_댓글을_등록한다(accessToken, feedCollectionId).jsonPath().getString("id");
    }

    public static ExtractableResponse<Response> 피드_컬렉션에_댓글을_수정한다(
            String accessToken,
            String feedCollectionId,
            String id,
            RequestSpecification spec
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("content", "수정된 댓글 내용");
        return RestAssured
                .given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .body(body).contentType("application/json")
                .when()
                .put("/api/feed_collections/{ignoredFeedCollectionId}/comments/{id}", feedCollectionId, id)
                .then()
                .log().all()
                .extract();
    }
}
