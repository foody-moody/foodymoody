package com.foodymoody.be.acceptance.feed_comment_like;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FeedCommentLikeSteps {

    public static ExtractableResponse<Response> 댓글에_좋아요를_취소한다(
            String commentId,
            String feedId,
            String accessToken,
            RequestSpecification spec
    ) {
        return RestAssured
                .given().log().all().spec(spec).auth().oauth2(accessToken).contentType("application/json")
                .when().delete("/api/feed/{feedId}/comments/{commentId}/likes", feedId, commentId)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 댓글에_좋아요를_누른다(
            String commentId,
            String feedId,
            String accessToken,
            RequestSpecification spec
    ) {
        return RestAssured
                .given().log().all().spec(spec).auth().oauth2(accessToken).contentType("application/json")
                .when().post("/api/feed/{feedId}/comments/{commentId}/likes", feedId, commentId)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 댓글에_좋아요를_누른다(String commentId, String feedId, String accessToken) {
        return 댓글에_좋아요를_누른다(commentId, feedId, accessToken, new RequestSpecBuilder().build());
    }

}
