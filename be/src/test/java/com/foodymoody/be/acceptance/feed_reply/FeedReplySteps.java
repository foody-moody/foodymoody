package com.foodymoody.be.acceptance.feed_reply;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;

public class FeedReplySteps {

    public static ExtractableResponse<Response> 댓글에_댓글을_등록한다(
            String feedId,
            String commentId,
            String accessToken,
            RequestSpecification spec
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("content", "댓글 내용");
        return RestAssured.given().spec(spec).log().all().auth().oauth2(accessToken)
                .body(body).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/feed/{feedId}/comments/{commentId}", feedId, commentId)
                .then().log().all()
                .extract();
    }


    public static ExtractableResponse<Response> 댓글의_댓글을_조회한다(String commentId, String feedId) {
        return 댓글의_댓글을_조회한다(commentId, feedId, new RequestSpecBuilder().build());
    }

    public static ExtractableResponse<Response> 댓글의_댓글을_조회한다(
            String commentId,
            String feedId,
            RequestSpecification spec
    ) {
        return RestAssured.given().spec(spec).log().all()
                .params(Map.of("page", "0", "size", "10"))
                .when().get("/api/feed/{feedId}/comments/{commentId}/replies", feedId, commentId)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 댓글의_댓글을_조회한다(
            String commentId,
            String feedId,
            String accessToken,
            RequestSpecification spec
    ) {
        return RestAssured.given().spec(spec).log().all().auth().oauth2(accessToken)
                .params(Map.of("page", "0", "size", "10"))
                .when().get("/api/feed/{feedId}/comments/{commentId}/replies", feedId, commentId)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 댓글에_댓글을_등록한다(String feedId, String commentId, String accessToken) {
        return 댓글에_댓글을_등록한다(feedId, commentId, accessToken, new RequestSpecBuilder().build());
    }

    public static String 댓글에_댓글을_등록하고_아이디를_가져온다(String feedId, String commentId, String accessToken) {
        return 댓글에_댓글을_등록한다(feedId, commentId, accessToken, new RequestSpecBuilder().build()).jsonPath()
                .getString("id");
    }

}
