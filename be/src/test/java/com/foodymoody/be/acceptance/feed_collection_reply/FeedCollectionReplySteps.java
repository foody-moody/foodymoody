package com.foodymoody.be.acceptance.feed_collection_reply;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class FeedCollectionReplySteps {

    public static ExtractableResponse<Response> 피드_컬렉션_댓글에_대댓글을_등록한다(
            String commentId,
            String feedCollectionId,
            String accessToken,
            RequestSpecification spec
    ) {
        Map<String, String> body = Map.of("content", "대댓글입니다.");
        return RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .body(body).contentType("application/json")
                .when()
                .post(
                        "/api/feed_collections/{feedCollectionId}/comments/{commentId}/replies", feedCollectionId,
                        commentId
                )
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드_컬렉션_댓글에_대댓글을_등록한다(
            String commentId,
            String feedCollectionId,
            String accessToken
    ) {
        return 피드_컬렉션_댓글에_대댓글을_등록한다(commentId, feedCollectionId, accessToken, new RequestSpecBuilder().build());
    }

    public static String 피드_컬렉션_댓글에_대댓글을_등록하고_아이디를_반환한다(
            String commentId,
            String feedCollectionId,
            String accessToken
    ) {
        return 피드_컬렉션_댓글에_대댓글을_등록한다(commentId, feedCollectionId, accessToken, new RequestSpecBuilder().build())
                .jsonPath().getString("id");
    }

    public static ExtractableResponse<Response> 피드_컬렉션_댓글의_대댓글을_삭제한다(
            String feedCollectionId,
            String commentId,
            String replyId,
            String accessToken,
            RequestSpecification spec
    ) {
        return RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .when()
                .delete(
                        "/api/feed_collections/{feedCollectionId}/comments/{commentId}/replies/{replyId}",
                        feedCollectionId, commentId, replyId
                )
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드_컬렉션_댓글의_대댓글을_수정한다(
            String feedCollectionId,
            String commentId,
            String replyId,
            String accessToken,
            RequestSpecification spec
    ) {
        Map<String, String> body = Map.of("content", "수정된 대댓글입니다.");
        return RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .body(body).contentType("application/json")
                .when()
                .put("/api/feed_collections/{feedCollectionId}/comments/{commentId}/replies/{replyId}",
                        feedCollectionId, commentId, replyId
                )
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드_컬렉션_댓글의_대댓글을_조회한다(
            String feedCollectionId,
            String commentId,
            String accessToken,
            RequestSpecification spec
    ) {
        return RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .when()
                .get(
                        "/api/feed_collections/{feedCollectionId}/comments/{commentId}/replies", feedCollectionId,
                        commentId
                )
                .then().log().all()
                .extract();
    }

}
