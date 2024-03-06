package com.foodymoody.be.acceptance.feed_collection_reply_like;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FeedCollectionReplyLikeSteps {

    public static ExtractableResponse<Response> 피드_컬렉션_대댓글에_좋아요를_등록한다(
            String feedCollectionId,
            String commentId,
            String replyId,
            String accessToken
    ) {
        return 피드_컬렉션_대댓글에_좋아요를_등록한다(
                feedCollectionId, commentId, replyId, accessToken, new RequestSpecBuilder().build());
    }

    public static ExtractableResponse<Response> 피드_컬렉션_대댓글에_좋아요를_등록한다(
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
                .post("/api/feed_collections/{feedCollectionId}/comments/{commentId}/replies/{replyId}/likes",
                      feedCollectionId, commentId, replyId
                )
                .then().log().all()
                .extract();
    }

    public static String 피드_컬렉션_대댓글에_좋아요를_등록하고_아이디를_반환한다(
            String feedCollectionId,
            String commentId,
            String replyId,
            String accessToken
    ) {
        return 피드_컬렉션_대댓글에_좋아요를_등록한다(
                feedCollectionId,
                commentId,
                replyId,
                accessToken,
                new RequestSpecBuilder().build()
        )
                .jsonPath().getString("id");
    }

    public static ExtractableResponse<Response> 피드_컬렉션_대댓글에_좋아요를_취소한다(
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
                        "/api/feed_collections/{feedCollectionId}/comments/{commentId}/replies/{replyId}/likes",
                        feedCollectionId, commentId, replyId
                )
                .then().log().all()
                .extract();
    }
}
