package com.foodymoody.be.acceptance.feed_collection_comment_like;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FeedCollectionCommentLikeSteps {

    public static ExtractableResponse<Response> 피드_컬렉션_댓글에_좋아요를_등록한다(
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
                .post(
                        "/api/feed_collections/{feedCollectionId}/comments/{commentId}/likes", feedCollectionId,
                        commentId
                )
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드_컬렉션_댓글에_좋아요를_등록한다(
            String feedCollectionId,
            String commentId,
            String accessToken
    ) {
        return 피드_컬렉션_댓글에_좋아요를_등록한다(feedCollectionId, commentId, accessToken, new RequestSpecBuilder().build());
    }

    public static ExtractableResponse<Response> 피드_컬렉션_댓글에_좋아요를_취소한다(
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
                .delete(
                        "/api/feed_collections/{feedCollectionId}/comments/{commentId}/likes", feedCollectionId,
                        commentId
                )
                .then().log().all()
                .extract();
    }
}
