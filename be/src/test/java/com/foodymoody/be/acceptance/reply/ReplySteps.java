package com.foodymoody.be.acceptance.reply;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;

public class ReplySteps {

    public static ExtractableResponse<Response> 댓글에_댓글을_등록한다(String feedId, String commentId, String accessToken,
            RequestSpecification spec) {
        Map<String, Object> body = new HashMap<>();
        body.put("content", "댓글 내용");
        body.put("feedId", feedId);
        return RestAssured.given().spec(spec).log().all().auth().oauth2(accessToken)
                .body(body).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/comments/{commentId}", commentId)
                .then().log().all()
                .extract();
    }


    public static ExtractableResponse<Response> 댓글의_댓글을_조회한다(String commentId) {
        return 댓글의_댓글을_조회한다(commentId, new RequestSpecBuilder().build());
    }

    public static ExtractableResponse<Response> 댓글의_댓글을_조회한다(String commentId, RequestSpecification spec) {
        return RestAssured.given().spec(spec).log().all()
                .params(Map.of("page", "0", "size", "10"))
                .when().get("/api/comments/{commentId}/replies", commentId)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 댓글에_댓글을_등록한다(String feedId, String commentId, String accessToken) {
        return 댓글에_댓글을_등록한다(feedId, commentId, accessToken, new RequestSpecBuilder().build());
    }

}
