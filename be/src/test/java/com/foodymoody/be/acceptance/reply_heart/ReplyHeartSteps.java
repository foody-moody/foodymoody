package com.foodymoody.be.acceptance.reply_heart;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ReplyHeartSteps {

    static ExtractableResponse<Response> 좋아요를_누린다(String accessToken, String commentId, String replyId,
            RequestSpecification spec) {
        return RestAssured
                .given(spec).auth().oauth2(accessToken)
                .when().post("/api/comments/{commentId}/replies/{replyId}/likes", commentId, replyId)
                .then().log().all()
                .extract();
    }

    static ExtractableResponse<Response> 좋아요를_누린다(String accessToken, String commentId, String replyId) {
        return 좋아요를_누린다(accessToken, commentId, replyId, new RequestSpecBuilder().build());
    }

    static ExtractableResponse<Response> 좋아요를_취소한다(String accessToken, String commentId, String replyId,
            RequestSpecification spec) {
        return RestAssured
                .given(spec).auth().oauth2(accessToken)
                .when().delete("/api/comments/{commentId}/replies/{replyId}/likes", commentId, replyId)
                .then().log().all()
                .extract();
    }
}
