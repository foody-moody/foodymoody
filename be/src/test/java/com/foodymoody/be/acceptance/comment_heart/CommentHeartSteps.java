package com.foodymoody.be.acceptance.comment_heart;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommentHeartSteps {

    public static ExtractableResponse<Response> 댓글에_좋아요를_취소한다(String accessToken, String commentId,
            RequestSpecification spec) {
        return RestAssured
                .given().log().all().spec(spec).auth().oauth2(accessToken).contentType("application/json")
                .when().delete("/api/comments/{commentId}/hearts", commentId)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 댓글에_좋아요를_누른다(String accessToken, String commentId,
            RequestSpecification spec) {
        return RestAssured
                .given().log().all().spec(spec).auth().oauth2(accessToken).contentType("application/json")
                .when().post("/api/comments/{commentId}/hearts", commentId)
                .then().log().all()
                .extract();
    }

}
