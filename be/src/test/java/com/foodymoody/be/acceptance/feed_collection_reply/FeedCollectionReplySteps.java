package com.foodymoody.be.acceptance.feed_collection_reply;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
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

    public static String 피드_컬렉션_댓글에_대댓글을_등록하고_아이디를_반환한다(
            String accessToken,
            String commentId
    ) {
        return 피드_컬렉션_댓글에_대댓글을_등록한다(accessToken, commentId, new RequestSpecBuilder().build())
                .jsonPath().getString("id");
    }

    public static ExtractableResponse<Response> 피드_컬렉션_댓글의_대댓글을_삭제한다(
            String accessToken,
            String replyId,
            String commentId,
            RequestSpecification spec
    ) {
        return RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .when()
                .delete("/api/feed_collections/{commentId}/replies/{replyId}", commentId, replyId)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드_컬렉션_댓글의_대댓글을_수정한다(
            String accessToken,
            String replyId,
            String commentId,
            RequestSpecification spec
    ) {
        Map<String, String> body = Map.of("content", "수정된 대댓글입니다.");
        return RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .body(body).contentType("application/json")
                .when()
                .put("/api/feed_collections/{commentId}/replies/{replyId}", commentId, replyId)
                .then().log().all()
                .extract();
    }
}
