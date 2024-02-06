package com.foodymoody.be.acceptance.feed_reply_like;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FeedReplyLikeSteps {

    public static ExtractableResponse<Response> 대댓글에_좋아요를_추가한다(
            String feedId,
            String commentId,
            String replyId,
            String accessToken
    ) {
        return 대댓글에_좋아요를_추가한다(
                feedId,
                commentId,
                replyId,
                accessToken,
                new RequestSpecBuilder().build()
        );
    }

    static ExtractableResponse<Response> 대댓글에_좋아요를_추가한다(
            String feedId,
            String commentId,
            String replyId,
            String accessToken,
            RequestSpecification spec
    ) {
        return RestAssured
                .given(spec).auth().oauth2(accessToken)
                .when()
                .post("/api/feed/{feedId}/comments/{commentId}/replies/{replyId}/likes", feedId, commentId, replyId)
                .then().log().all()
                .extract();
    }

    static ExtractableResponse<Response> 대댓글의_좋아요를_취소한다(
            String feedId,
            String commentId,
            String replyId,
            String accessToken,
            RequestSpecification spec
    ) {
        return RestAssured
                .given(spec).auth().oauth2(accessToken)
                .when()
                .delete("/api/feed/{feedId}/comments/{commentId}/replies/{replyId}/likes", feedId, commentId, replyId)
                .then().log().all()
                .extract();
    }
}
